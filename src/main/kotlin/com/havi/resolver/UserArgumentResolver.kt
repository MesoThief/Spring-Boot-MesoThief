package com.havi.resolver

import com.havi.annotation.SocialUser
import com.havi.domain.User
import com.havi.domain.enums.SocialType
import com.havi.domain.enums.SocialType.FACEBOOK
import com.havi.domain.enums.SocialType.GOOGLE
import com.havi.domain.enums.SocialType.KAKAO
import com.havi.repository.UserRepository
import org.springframework.core.MethodParameter
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import java.time.LocalDateTime
import javax.servlet.http.HttpSession

@Component
class UserArgumentResolver(private val userRepository: UserRepository): HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return (parameter.getParameterAnnotation(SocialUser::class.java) != null)
            && (parameter.parameterType == User::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?,
    ): Any? {
        val session = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request.session
        val user = session.getAttribute("user") as User
        return getUser(user, session)
    }

    private fun getUser(user: User, session: HttpSession): User {
        if (user == null) {
            try {
                val authentication = SecurityContextHolder.getContext().authentication as OAuth2AuthenticationToken
                val map = authentication.principal.attributes
                val convertUser = convertUser(authentication.authorizedClientRegistrationId, map)

                var authorizedUser = userRepository.findByEmail(convertUser!!.getEmail())
                if (authorizedUser == null) authorizedUser = userRepository.save(convertUser)

                setRoleIfNotSame(authorizedUser, authentication, map)
                return authorizedUser
            } catch (e: ClassCastException) {
                return user
            }
        }
        return user
    }

    private fun convertUser(authority: String, map: Map<String, Any>): User? {
        when (authority) {
            FACEBOOK.roleType -> return getModernUser(FACEBOOK, map)
            GOOGLE.roleType -> return getModernUser(GOOGLE, map)
            KAKAO.roleType -> return getKakaoUser(map)
            else -> return null
        }
    }

    private fun getModernUser(socialType: SocialType, map: Map<String, Any>): User {
        return User(
            name = map["name"].toString(),
            email = map["email"].toString(),
            principal = map["id"].toString(),
            socialType = socialType,
            createdDate = LocalDateTime.now(),
        )
    }

    private fun getKakaoUser(map: Map<String, Any>): User {
        val propertyMap = map["properties"] as HashMap<String, String>
        return User(
            name = propertyMap["nickname"] ?: "",
            email = map["kaccount_email"].toString(),
            principal = map["id"].toString(),
            socialType = KAKAO,
            createdDate = LocalDateTime.now(),
        )
    }

    private fun setRoleIfNotSame(
        user: User,
        authentication: OAuth2AuthenticationToken,
        map: Map<String, Any>,
    ) {
        val roleType = user.getSocialType().roleType
        if (!authentication.authorities.contains(SimpleGrantedAuthority(roleType))) {
            SecurityContextHolder.getContext().authentication =
                UsernamePasswordAuthenticationToken(
                    map,
                    "N/A",
                    AuthorityUtils.createAuthorityList(roleType),
                )
        }
    }
}

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
        val user = session.getAttribute("user")
        return getUser(user, session)
    }

    private fun getUser(userFromSession: Any?, session: HttpSession): User {
        if (userFromSession != null) return userFromSession as User
        else {
            val defaultUser = User().apply {
                name = "name"
                email = "email@email.com"
                socialType = FACEBOOK
                createdDate = LocalDateTime.now()
            }

            return try {
                val authentication = SecurityContextHolder.getContext().authentication as OAuth2AuthenticationToken
                val map = authentication.principal.attributes
                val convertUser = convertUser(authentication.authorizedClientRegistrationId, map)

                val user = userRepository.findByEmail(convertUser?.email ?: "")
                    ?: userRepository.save(convertUser ?: defaultUser)

                setRoleIfNotSame(user, authentication, map)
                session.setAttribute("user", user)
                user
            } catch (e: ClassCastException) {
                defaultUser
            }
        }
    }

    private fun convertUser(authority: String, map: Map<String, Any>): User? {
        return when (authority) {
            FACEBOOK.roleType -> getModernUser(FACEBOOK, map)
            GOOGLE.roleType -> getModernUser(GOOGLE, map)
            KAKAO.roleType -> getKakaoUser(map)
            else -> null
        }
    }

    private fun getModernUser(socialType: SocialType, map: Map<String, Any>): User {
        return User().apply {
            name = map["name"].toString()
            email = map["email"].toString()
            principal = map["id"].toString()
            this.socialType = socialType
            createdDate = LocalDateTime.now()
        }
    }

    private fun getKakaoUser(map: Map<String, Any>): User {
        val propertyMap = map["properties"] as HashMap<String, String>
        return User().apply {
            name = propertyMap["nickname"] ?: ""
            email = map["kaccount_email"].toString()
            principal = map["id"].toString()
            this.socialType = KAKAO
            createdDate = LocalDateTime.now()
        }
    }

    private fun setRoleIfNotSame(
        user: User,
        authentication: OAuth2AuthenticationToken,
        map: Map<String, Any>,
    ) {
        val roleType = user.socialType.roleType
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

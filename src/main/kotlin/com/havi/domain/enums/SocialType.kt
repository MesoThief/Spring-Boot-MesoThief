package com.havi.domain.enums

enum class SocialType(name: String) {

    FACEBOOK("facebook"),
    GOOGLE("google"),
    KAKAO("kakao");

    private val ROLE_PREFIX: String = "ROLE_"

    fun getRoleType(): String {
        return ROLE_PREFIX + name.uppercase()
    }

    fun getValue(): String {
        return name
    }

    fun isEquals(authority: String): Boolean {
        return getRoleType() == authority
    }
}

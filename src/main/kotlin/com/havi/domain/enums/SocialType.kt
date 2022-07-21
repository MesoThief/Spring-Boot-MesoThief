package com.havi.domain.enums

enum class SocialType(val value: String) {
    FACEBOOK("facebook"),
    GOOGLE("google"),
    KAKAO("kakao"),
    ;

    private val ROLE_PREFIX: String = "ROLE_"

    val roleType get() = ROLE_PREFIX + value.uppercase()
}

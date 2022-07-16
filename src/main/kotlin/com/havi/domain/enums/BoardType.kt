package com.havi.domain.enums

enum class BoardType(private val value: String) {
    notice("공지사항"),
    free("자유게시판");

    fun getValue(): String = value
}

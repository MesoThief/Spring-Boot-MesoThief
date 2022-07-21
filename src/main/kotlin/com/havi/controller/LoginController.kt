package com.havi.controller

import com.havi.annotation.SocialUser
import com.havi.domain.User
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class LoginController {
    @GetMapping("/login")
    fun login(): String {
        return "login"
    }

    @GetMapping("/loginSuccess")
    fun loginComplete(@SocialUser user: User): String {
        return "redirect:/board/list"
    }
}

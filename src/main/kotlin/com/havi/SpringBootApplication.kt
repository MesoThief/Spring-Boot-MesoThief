package com.havi

import com.havi.domain.Board
import com.havi.domain.User
import com.havi.domain.enums.BoardType
import com.havi.repository.BoardRepository
import com.havi.repository.UserRepository
import com.havi.resolver.UserArgumentResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import java.time.LocalDateTime

@SpringBootApplication
class SpringBootApplication: WebMvcConfigurerAdapter() {

    @Autowired
    private lateinit var userArgumentResolver: UserArgumentResolver

    override fun addArgumentResolvers(argumentResolvers: MutableList<HandlerMethodArgumentResolver>) {
        argumentResolvers.add(userArgumentResolver)
        @Bean
        @Throws(Exception::class)
        fun runner(
            userRepository: UserRepository,
            boardRepository: BoardRepository,
        ): CommandLineRunner = CommandLineRunner {
            val user = userRepository.save(
                User(
                    name = "havi",
                    password = "test",
                    email = "havi@gmail.com",
                ),
            )

            for (index in 1..200) {
                boardRepository.save(
                    Board(
                        title = "게시글$index",
                        subTitle = "순서$index",
                        content = "콘텐츠",
                        boardType = BoardType.FREE,
                        user = user,
                    ),
                )
            }
        }
    }
}

fun main(args: Array<String>) {
    runApplication<com.havi.SpringBootApplication>(*args)
}

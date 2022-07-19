package com.havi

import com.havi.domain.Board
import com.havi.domain.User
import com.havi.domain.enums.BoardType
import com.havi.repository.BoardRepository
import com.havi.repository.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class SpringBootApplication {
    @Bean
    fun runner(
        userRepository: UserRepository,
        boardRepository: BoardRepository
    ): CommandLineRunner = CommandLineRunner {
            val user = userRepository.save(
                User(
                    name = "havi",
                    password = "test",
                    email = "havi@gmail.com",
                )
            )

            for (index in 1..200) {
                boardRepository.save(
                    Board(
                        title = "게시글$index",
                        subTitle = "순서$index",
                        content = "콘텐츠",
                        boardType = BoardType.FREE,
                        user = user,
                    )
                )
            }
        }
}

fun main(args: Array<String>) {
    runApplication<com.havi.SpringBootApplication>(*args)
}

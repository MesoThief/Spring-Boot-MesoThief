package com.havi.repository

import com.havi.domain.Board
import com.havi.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BoardRepository : JpaRepository<Board, Long> {
    fun findByUser(user: User): Board
}

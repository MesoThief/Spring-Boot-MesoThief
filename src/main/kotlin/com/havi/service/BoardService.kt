package com.havi.service

import com.havi.domain.Board
import com.havi.repository.BoardRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BoardService(
    private val boardRepository: BoardRepository
) {
    fun findBoardList(pageable: Pageable): Page<Board> {
        val pageRequest = PageRequest.of(
            if (pageable.pageNumber <= 0) 0
            else pageable.pageNumber - 1,
            pageable.pageSize
        )
        return boardRepository.findAll(pageRequest)
    }

    fun findBoardByIdx(idx: Long): Board {
        return boardRepository.findById(idx).get()
    }

    fun saveAndUpdateBoard(board: Board): Board {
        return boardRepository.save(board)
    }
}

package com.havi.domain

import com.havi.domain.enums.BoardType
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table
class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idx: Long = 0

    lateinit var title: String
    lateinit var subTitle: String
    lateinit var content: String
    lateinit var boardType: BoardType
    lateinit var createdDate: LocalDateTime
    lateinit var updatedDate: LocalDateTime
    @OneToOne(fetch = FetchType.LAZY)
    lateinit var user: User
}

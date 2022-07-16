package com.havi.domain

import com.havi.domain.enums.BoardType
import lombok.Builder
import lombok.Getter
import lombok.NoArgsConstructor
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.Table

@Getter
@NoArgsConstructor
@Entity
@Table
class Board @Builder constructor(
    @field:Column private val title: String = "",
    @field:Column private val subTitle: String = "",
    @field:Column private val content: String = "",
    @field:Enumerated(
        EnumType.STRING,
    ) @field:Column private val boardType: BoardType = BoardType.free,
    @field:Column private val createdDate: LocalDateTime = LocalDateTime.now(),
    @field:Column private val updatedDate: LocalDateTime = LocalDateTime.now(),
    @field:OneToOne(
        fetch = FetchType.LAZY,
    ) private val user: User = User(),
):
    Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val idx: Long? = null
}

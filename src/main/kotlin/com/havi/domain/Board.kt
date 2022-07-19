package com.havi.domain

import com.havi.domain.enums.BoardType
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

@Entity
@Table
class Board(
    @Column
    var title: String,

    @Column
    var subTitle: String,

    @Column
    var content: String,

    @Column
    @Enumerated(EnumType.STRING)
    var boardType: BoardType,

    @OneToOne(fetch = FetchType.LAZY)
    var user: User,
): AuditLoggingBase(), java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idx: Long? = null
}

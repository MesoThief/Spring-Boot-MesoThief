package com.havi.domain

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Table
@Entity
class Book(
    @Column
    var title: String,

    @Column
    var publishedAt: LocalDateTime?,
) {
    @Id
    @Column
    @GeneratedValue
    var idx: Int = 0
}

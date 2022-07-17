package com.havi.domain

import com.havi.domain.enums.SocialType
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user_info")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idx: Long = 0
    lateinit var name: String
    lateinit var password: String
    lateinit var email: String

    lateinit var principal: String

    @Enumerated(EnumType.STRING)
    lateinit var socialType: SocialType

    lateinit var createdDate: LocalDateTime
    lateinit var updatedDate: LocalDateTime
}

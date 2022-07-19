package com.havi.domain

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user_info")
class User(

    @Column
    var name: String = "",

    @Column
    var password: String = "",

    @Column
    var email: String = "",

    ): AuditLoggingBase(), Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idx: Long? = null
}

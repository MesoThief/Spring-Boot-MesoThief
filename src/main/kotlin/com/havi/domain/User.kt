package com.havi.domain

import com.havi.domain.enums.SocialType
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
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

    @Column
    var principal: String = "",

    @Column
    @Enumerated(EnumType.STRING)
    var socialType: SocialType = SocialType.FACEBOOK,

    ): AuditLoggingBase(), Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idx: Long? = null
}

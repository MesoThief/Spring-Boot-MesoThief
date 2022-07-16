package com.havi.domain

import com.havi.domain.enums.SocialType
import lombok.Builder
import lombok.Getter
import lombok.NoArgsConstructor
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Getter
@NoArgsConstructor
@Entity
@Table
class User @Builder constructor(
    @field:Column private val name: String = "",
    @field:Column private val password: String = "",
    @field:Column private val email: String = "",
    @field:Column private val principal: String = "",
    @field:Enumerated(
        EnumType.STRING,
    ) @field:Column private val socialType: SocialType = SocialType.GOOGLE,
    @field:Column private val createdDate: LocalDateTime = LocalDateTime.now(),
    @field:Column private val updatedDate: LocalDateTime = LocalDateTime.now(),
):
    Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val idx: Long? = null

    fun getEmail() = email
    fun getSocialType() = socialType

}

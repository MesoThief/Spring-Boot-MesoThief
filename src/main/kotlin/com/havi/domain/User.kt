package com.havi.domain

import com.havi.domain.enums.SocialType
import lombok.Getter
import lombok.NoArgsConstructor
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*

@NoArgsConstructor
@Getter
@Entity
@Table
class User(
    @Column
    private val name: String,

    @Column
    private val password: String,

    @Column
    private val email: String,

    @Column
    private val principal: String,

    @Column
    private val socialType: SocialType,

    @Column
    private val createDate: LocalDateTime,

    @Column
    private val updatedDate: LocalDateTime,
) : Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val idx: Long = 0L
}

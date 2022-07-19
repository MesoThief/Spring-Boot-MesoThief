package com.havi.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class AuditLoggingBase {
    @Column
    @CreatedDate
    lateinit var createdDate: LocalDateTime

    @Column
    @CreatedDate
    lateinit var updatedDate: LocalDateTime
}

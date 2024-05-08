package kr.co.nottodo.persistence.rdb.domain.base

import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@EntityListeners(value = [AuditingEntityListener::class])
@MappedSuperclass
abstract class BaseEntity {

    @CreatedDate
    open val createdDate: LocalDateTime = LocalDateTime.now()

    @LastModifiedDate
    open var modifiedDate: LocalDateTime = LocalDateTime.now()
}
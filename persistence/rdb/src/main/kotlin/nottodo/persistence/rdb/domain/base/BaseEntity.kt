package nottodo.persistence.rdb.domain.base

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
    open val createdAt: LocalDateTime = LocalDateTime.now()

    @LastModifiedDate
    open var modifiedAt: LocalDateTime = LocalDateTime.now()
}

package nottodo.persistence.rdb.domain.user.repository

import nottodo.persistence.rdb.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
}

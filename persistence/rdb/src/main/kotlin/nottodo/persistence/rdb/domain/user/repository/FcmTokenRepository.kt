package nottodo.persistence.rdb.domain.user.repository

import nottodo.persistence.rdb.domain.user.entity.FcmToken
import org.springframework.data.jpa.repository.JpaRepository

interface FcmTokenRepository: JpaRepository<FcmToken, Long> {
    fun findByToken(token: String): FcmToken?
}

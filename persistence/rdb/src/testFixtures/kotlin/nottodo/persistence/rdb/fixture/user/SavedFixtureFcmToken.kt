package nottodo.persistence.rdb.fixture.user

import nottodo.persistence.rdb.domain.user.entity.FcmToken
import nottodo.persistence.rdb.domain.user.entity.User
import nottodo.persistence.rdb.domain.user.repository.FcmTokenRepository

object SavedFixtureFcmToken {
    fun create(repository: FcmTokenRepository, user: User): FcmToken {
        val recommendMission = FixtureFcmToken.create(user = user)
        return repository.save(recommendMission)
    }
}

package nottodo.persistence.rdb.fixture.recommend

import nottodo.common.random.RandomUtil
import nottodo.persistence.rdb.domain.user.entity.FcmToken
import nottodo.persistence.rdb.domain.user.entity.User

object FixtureFcmToken {
    fun create(
        user: User,
        token: String = RandomUtil.string()
    ): FcmToken {
        return FcmToken.of(
            user = user,
            token = token
        )
    }
}

package nottodo.persistence.rdb.fixture.user

import nottodo.common.random.RandomUtil
import nottodo.persistence.rdb.domain.user.entity.SocialType
import nottodo.persistence.rdb.domain.user.entity.User

object FixtureUser {
    fun create(
        email: String =RandomUtil.string(),
        socialId: String = RandomUtil.string(),
        socialType: SocialType = SocialType.TEST,
        name: String = RandomUtil.string(),
        image: String = RandomUtil.string()
    ): User {
        return User.of(
            email = email,
            socialId = socialId,
            socialType = socialType,
            name = name,
            image = image,
        )
    }
}

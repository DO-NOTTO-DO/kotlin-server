package nottodo.persistence.rdb.fixture.recommend

import nottodo.common.random.RandomUtil
import nottodo.persistence.rdb.domain.recommend.entity.RecommendMission

object FixtureRecommendMission {
    fun create(
        title: String = RandomUtil.string(),
        situation: String = RandomUtil.string(),
        description: String = RandomUtil.string(),
        image: String = RandomUtil.string(),
    ) : RecommendMission {
        return RecommendMission.of(
            title = title,
            situation = situation,
            description = description,
            image = image
        )
    }
}

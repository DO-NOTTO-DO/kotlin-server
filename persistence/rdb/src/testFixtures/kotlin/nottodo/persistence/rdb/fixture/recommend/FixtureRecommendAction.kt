package nottodo.persistence.rdb.fixture.recommend

import nottodo.common.random.RandomUtil
import nottodo.persistence.rdb.domain.recommend.entity.RecommendAction
import nottodo.persistence.rdb.domain.recommend.entity.RecommendMission

object FixtureRecommendAction {
    fun create(
        recommendMission: RecommendMission,
        name: String = RandomUtil.string(),
        description: String = RandomUtil.string()
    ): RecommendAction {
        return RecommendAction.of(
            recommendMission = recommendMission,
            name = name,
            description = description
        )
    }
}

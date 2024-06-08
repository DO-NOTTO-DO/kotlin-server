package nottodo.persistence.rdb.fixture.recommend

import nottodo.persistence.rdb.domain.recommend.entity.RecommendAction
import nottodo.persistence.rdb.domain.recommend.entity.RecommendMission
import nottodo.persistence.rdb.domain.recommend.repository.RecommendActionRepository

object SavedFixtureRecommendAction {
    fun create(repository: RecommendActionRepository, recommendMission: RecommendMission): RecommendAction {
        val recommendAction = FixtureRecommendAction.create(recommendMission)
        return repository.save(recommendAction)
    }
}

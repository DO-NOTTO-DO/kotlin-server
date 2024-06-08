package nottodo.persistence.rdb.fixture.recommend

import nottodo.persistence.rdb.domain.recommend.entity.RecommendMission
import nottodo.persistence.rdb.domain.recommend.repository.RecommendMissionRepository

object SavedFixtureRecommendMission {
    fun create(repository: RecommendMissionRepository): RecommendMission {
        val recommendMission = FixtureRecommendMission.create()
        return repository.save(recommendMission)
    }
}

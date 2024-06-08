package nottodo.persistence.rdb.domain.recommend.repository

import nottodo.persistence.rdb.domain.recommend.entity.QRecommendAction.recommendAction
import nottodo.persistence.rdb.domain.recommend.entity.QRecommendMission.recommendMission
import nottodo.persistence.rdb.domain.recommend.entity.RecommendAction
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class RecommendActionQueryRepository: QuerydslRepositorySupport(RecommendAction::class.java) {
    fun fetchByRecommendMissionId(recommendMissionId: Long): List<RecommendAction> {
        return from(recommendAction)
            .leftJoin(recommendAction.recommendMission, recommendMission).fetchJoin()
            .where(recommendAction.recommendMission.id.eq(recommendMissionId))
            .fetch()
    }
}

package nottodo.recommend.service

import nottodo.persistence.rdb.common.ParentEntityUtil
import nottodo.persistence.rdb.domain.recommend.repository.RecommendActionQueryRepository
import nottodo.recommend.response.RecommendActionsOfMissionResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RecommendService(
    private val recommendActionQueryRepository: RecommendActionQueryRepository
) {

    @Transactional(readOnly = true)
    fun getRecommendActionsByRecommendMission(recommendMissionId: Long): RecommendActionsOfMissionResponse {
        val recommendActions = recommendActionQueryRepository.fetchByRecommendMissionId(recommendMissionId)
        val recommendMission = ParentEntityUtil.validateAndExtractParent(recommendActions) { it.recommendMission }
        return RecommendActionsOfMissionResponse.of(recommendMission, recommendActions)
    }
}

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
        // TODO: RecommendMission 이 있는데, 거기에 딸린 RecommendAction 이 없는 경우에 예외 발생. 해결해야하는가?
        val recommendActions = recommendActionQueryRepository.fetchByRecommendMissionId(recommendMissionId)
        val recommendMission = ParentEntityUtil.validateAndExtractParent(recommendActions) { it.recommendMission }
        return RecommendActionsOfMissionResponse.of(recommendMission, recommendActions)
    }
}

package nottodo.recommend.service

import nottodo.commonspring.exception.CustomNotFoundException
import nottodo.persistence.rdb.domain.recommend.repository.RecommendActionRepository
import nottodo.persistence.rdb.domain.recommend.repository.RecommendMissionRepository
import nottodo.recommend.response.RecommendActionsOfMissionResponse
import nottodo.recommend.response.RecommendMissionResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RecommendService(
    private val recommendActionRepository: RecommendActionRepository,
    private val recommendMissionRepository: RecommendMissionRepository
) {

    @Transactional(readOnly = true)
    fun getRecommendMissions(): List<RecommendMissionResponse> {
        val recommendMissions = recommendMissionRepository.findAll()
        return recommendMissions.map { RecommendMissionResponse.from(it) }
    }

    @Transactional(readOnly = true)
    fun getRecommendActionsByRecommendMission(recommendMissionId: Long): RecommendActionsOfMissionResponse {
        val recommendMission = recommendMissionRepository.findByIdOrNull(recommendMissionId)
            ?: throw CustomNotFoundException("해당 RecommendMission 이 존재하지 않습니다.")
        val recommendActions = recommendActionRepository.findByRecommendMissionId(recommendMissionId)
        return RecommendActionsOfMissionResponse.of(recommendMission, recommendActions)
    }
}

package nottodo.recommend.response

import nottodo.common.converter.NonNullConverter
import nottodo.persistence.rdb.domain.recommend.entity.RecommendAction
import nottodo.persistence.rdb.domain.recommend.entity.RecommendMission

data class RecommendActionsOfMissionResponse(
    val id: Long,
    val title: String,
    val recommendActions: List<RecommendActionResponse>
) {
    companion object {
        fun of(recommendMission: RecommendMission, recommendActions: List<RecommendAction>): RecommendActionsOfMissionResponse {
            return RecommendActionsOfMissionResponse(
                id = NonNullConverter.convert(recommendMission.id),
                title = recommendMission.title,
                recommendActions = recommendActions.map { RecommendActionResponse.from(it) }
            )
        }
    }
}

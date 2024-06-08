package nottodo.recommend.response

import nottodo.common.converter.NonNullConverter
import nottodo.persistence.rdb.domain.recommend.entity.RecommendMission

data class RecommendMissionResponse(
    val id: Long,
    val title: String,
    val situation: String,
    val description: String,
    val image: String
) {
    companion object {
        fun from(recommendMission: RecommendMission): RecommendMissionResponse {
            return RecommendMissionResponse(
                id = NonNullConverter.convert(recommendMission.id),
                title = recommendMission.title,
                situation = recommendMission.situation,
                description = recommendMission.description,
                image = recommendMission.image
            )
        }
    }
}

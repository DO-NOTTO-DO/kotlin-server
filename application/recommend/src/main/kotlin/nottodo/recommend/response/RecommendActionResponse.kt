package nottodo.recommend.response

import nottodo.persistence.rdb.domain.recommend.entity.RecommendAction

data class RecommendActionResponse(
    val name: String,
    val description: String?
) {
    companion object {
        fun from(recommendAction: RecommendAction): RecommendActionResponse {
            return RecommendActionResponse(
                name = recommendAction.name,
                description = recommendAction.description
            )
        }
    }
}

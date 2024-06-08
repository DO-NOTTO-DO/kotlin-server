package nottodo.api.domain.recommend.controller

import nottodo.commonspring.dto.response.ApiResponseBody
import nottodo.commonspring.dto.response.ResponseUtil
import nottodo.recommend.controller.RecommendControllerPath
import nottodo.recommend.response.RecommendActionsOfMissionResponse
import nottodo.recommend.service.RecommendService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class RecommendController(
    private val recommendService: RecommendService
) {

    @GetMapping(RecommendControllerPath.GET_RECOMMEND_ACTIONS_BY_RECOMMEND_MISSION)
    fun getRecommendActionsByRecommendMission(@PathVariable recommendMissionId: Long): ResponseEntity<ApiResponseBody<RecommendActionsOfMissionResponse>> {
        val data = recommendService.getRecommendActionsByRecommendMission(recommendMissionId)
        return ResponseUtil.ok(data, "성공")
    }
}

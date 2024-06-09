package nottodo.api.domain.mission.controller

import nottodo.api.config.security.resolver.Auth
import nottodo.commonspring.dto.response.ApiResponseBody
import nottodo.commonspring.dto.response.ResponseUtil
import nottodo.mission.controller.MissionControllerPath
import nottodo.mission.response.DailyMissionResponse
import nottodo.mission.service.DailyMissionService
import nottodo.persistence.rdb.domain.user.entity.User
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
class MissionController(
    private val dailyMissionService: DailyMissionService
) {

    @GetMapping(MissionControllerPath.GET_DAILY_MISSIONS)
    fun getDailyMissions(
        @PathVariable date: LocalDate,
        @Auth user: User
    ): ResponseEntity<ApiResponseBody<List<DailyMissionResponse>>> {
        val data = dailyMissionService.getTodayDailyMissions(date, user)
        return ResponseUtil.ok(data)
    }
}

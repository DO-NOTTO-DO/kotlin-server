package nottodo.api.domain.mission.controller

import nottodo.api.config.security.resolver.Auth
import nottodo.commonspring.dto.response.ApiResponseBody
import nottodo.commonspring.dto.response.ResponseUtil
import nottodo.mission.controller.MissionControllerPath
import nottodo.mission.request.MissionCreateRequest
import nottodo.mission.response.DailyMissionResponse
import nottodo.mission.service.DailyMissionService
import nottodo.mission.service.MissionService
import nottodo.persistence.rdb.domain.user.entity.User
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.time.LocalDate

@RestController
class MissionController(
    private val dailyMissionService: DailyMissionService,
    private val missionService: MissionService,
) {

    @GetMapping(MissionControllerPath.GET_DAILY_MISSIONS)
    fun getDailyMissions(
        @PathVariable date: LocalDate,
        @Auth userId: Long
    ): ResponseEntity<ApiResponseBody<List<DailyMissionResponse>>> {
        val data = dailyMissionService.getTodayDailyMissions(date, userId)
        return ResponseUtil.ok(data)
    }

    @PostMapping(MissionControllerPath.CREATE_MISSION)
    fun createMission(
        @RequestBody request: MissionCreateRequest,
        @Auth userId: Long
    ): ResponseEntity<ApiResponseBody<Nothing>> {
        val missionId = missionService.createMission(request = request, userId = userId)
        val uri = URI.create("${MissionControllerPath.GET_MISSION}/$missionId")
        return ResponseUtil.created(data = null, uri = uri)
    }
}

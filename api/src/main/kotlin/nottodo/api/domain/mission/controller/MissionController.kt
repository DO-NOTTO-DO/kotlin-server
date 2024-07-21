package nottodo.api.domain.mission.controller

import nottodo.api.config.security.resolver.Auth
import nottodo.commonspring.dto.response.ApiResponseBody
import nottodo.commonspring.dto.response.ResponseUtil
import nottodo.mission.controller.MissionControllerPath
import nottodo.mission.request.DailyMissionUpdateCompletionStatusRequest
import nottodo.mission.request.MissionCreateRequest
import nottodo.mission.request.MissionUpdateRequest
import nottodo.mission.response.DailyMissionCompletionStatusResponse
import nottodo.mission.response.DailyMissionDetailResponse
import nottodo.mission.response.DailyMissionResponse
import nottodo.mission.response.MissionTitleResponse
import nottodo.mission.service.DailyMissionService
import nottodo.mission.service.MissionService
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
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
        val data = dailyMissionService.getTodayDailyMissions(date = date, userId = userId)
        return ResponseUtil.ok(data)
    }

    @PostMapping(MissionControllerPath.CREATE_MISSION)
    fun createMission(
        @Validated @RequestBody request: MissionCreateRequest,
        @Auth userId: Long
    ): ResponseEntity<ApiResponseBody<Nothing>> {
        missionService.createMission(request = request, userId = userId)
        val uri = URI.create("/")
        return ResponseUtil.created(data = null, uri = uri)
    }

    @GetMapping(MissionControllerPath.GET_WEEKLY_COMPLETION_STATE)
    fun getWeeklyMissionCompletionRates(
        @PathVariable startDate: LocalDate,
        @Auth userId: Long
    ): ResponseEntity<ApiResponseBody<List<DailyMissionCompletionStatusResponse>>> {
        val data = dailyMissionService.getWeeklyMissionCompletionRates(startDate = startDate, userId = userId)
        return ResponseUtil.ok(data)
    }

    @GetMapping(MissionControllerPath.GET_MONTHLY_COMPLETION_STATE)
    fun getMonthlyMissionCompletionRates(
        @PathVariable month: String,
        @Auth userId: Long
    ): ResponseEntity<ApiResponseBody<List<DailyMissionCompletionStatusResponse>>> {
        val data = dailyMissionService.getMonthlyMissionCompletionRates(yearMonthInput = month, userId = userId)
        return ResponseUtil.ok(data)
    }

    @PatchMapping(MissionControllerPath.UPDATE_DAILY_MISSION_COMPLETION_STATUS)
    fun updateDailyMissionCompletionStatus(
        @PathVariable dailyMissionId: Long,
        @RequestBody request: DailyMissionUpdateCompletionStatusRequest,
        @Auth userId: Long
    ): ResponseEntity<ApiResponseBody<DailyMissionResponse>> {
        val data = dailyMissionService.updateDailyMissionCompletionStatus(
            dailyMissionId = dailyMissionId,
            request = request,
            userId = userId
        )
        val uri = UriComponentsBuilder
            .fromUriString(MissionControllerPath.GET_DAILY_MISSION)
            .buildAndExpand(dailyMissionId)
            .toUri()
        return ResponseUtil.created(data = data, uri = uri)
    }

    @GetMapping(MissionControllerPath.GET_RECENT_MISSIONS)
    fun getRecentMissionsTitle(@Auth userId: Long): ResponseEntity<ApiResponseBody<List<MissionTitleResponse>>> {
        val data = missionService.findRecentMissionsTitle(userId)
        return ResponseUtil.ok(data)
    }

    @GetMapping(MissionControllerPath.GET_DAILY_MISSION)
    fun getDailyMissionDetail(
        @PathVariable dailyMissionId: Long,
        @Auth userId: Long
    ): ResponseEntity<ApiResponseBody<DailyMissionDetailResponse>> {
        val data = missionService.getDailyMissionDetail(dailyMissionId = dailyMissionId, userId = userId)
        return ResponseUtil.ok(data)
    }

    @GetMapping(MissionControllerPath.GET_DAILY_MISSION_PLAN_DATES)
    fun getDailyMissionPlanDates(
        @PathVariable dailyMissionId: Long,
        @Auth userId: Long
    ): ResponseEntity<ApiResponseBody<List<String>>> {
        val data = dailyMissionService.getDailyMissionPlanDates(dailyMissionId = dailyMissionId, userId = userId)
        return ResponseUtil.ok(data)
    }

    @PutMapping(MissionControllerPath.UPDATE_MISSION)
    fun updateMission(
        @PathVariable dailyMissionId: Long,
        @RequestBody request: MissionUpdateRequest,
        @Auth userId: Long
    ): ResponseEntity<ApiResponseBody<Nothing>> {
        missionService.updateMission(dailyMissionId = dailyMissionId, request = request, userId = userId)
        val uri = URI.create("/")
        return ResponseUtil.created(data = null, uri = uri)
    }

    @DeleteMapping(MissionControllerPath.DELETE_DAILY_MISSION)
    fun deleteDailyMission(@PathVariable dailyMissionId: Long, @Auth userId: Long): ResponseEntity<ApiResponseBody<Nothing>> {
        dailyMissionService.deleteDailyMission(dailyMissionId = dailyMissionId, userId = userId)
        return ResponseUtil.ok(null)
    }
}

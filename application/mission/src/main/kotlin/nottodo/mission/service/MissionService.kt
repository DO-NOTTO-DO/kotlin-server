package nottodo.mission.service

import nottodo.common.converter.NonNullConverter
import nottodo.common.date.DateUtil
import nottodo.commonspring.exception.CustomBadRequestException
import nottodo.commonspring.exception.CustomNotFoundException
import nottodo.mission.request.DailyMissionUpdateCompletionStatusRequest
import nottodo.mission.request.MissionCreateRequest
import nottodo.mission.request.MissionDuplicateRequest
import nottodo.mission.request.MissionUpdateRequest
import nottodo.mission.response.DailyMissionCompletionStatusResponse
import nottodo.mission.response.DailyMissionDetailResponse
import nottodo.mission.response.DailyMissionResponse
import nottodo.mission.response.MissionTitleResponse
import nottodo.persistence.rdb.domain.mission.entity.DailyMission
import nottodo.persistence.rdb.domain.mission.entity.Mission
import nottodo.persistence.rdb.domain.mission.repository.DailyMissionQueryRepository
import nottodo.persistence.rdb.domain.mission.repository.DailyMissionRepository
import nottodo.persistence.rdb.domain.mission.repository.MissionQueryRepository
import nottodo.persistence.rdb.domain.mission.repository.MissionRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class MissionService(
    private val missionRepository: MissionRepository,
    private val missionQueryRepository: MissionQueryRepository,
    private val dailyMissionRepository: DailyMissionRepository,
    private val dailyMissionQueryRepository: DailyMissionQueryRepository,
) {

    @Transactional
    fun createMission(request: MissionCreateRequest, userId: Long): Long {
        val mission = Mission.of(
            title = request.title,
            situation = request.situation,
            goal = request.goal,
            userId = userId
        )
        validateDailyMissionsCount(userId = userId, dates = request.dates)
        val savedMission = missionRepository.save(mission)
        val dailyMissions = request.dates.map {
            DailyMission.of(
                mission = savedMission,
                date = it,
            )
        }
        dailyMissionRepository.saveAll(dailyMissions)
        return NonNullConverter.convert(dailyMissions[0].id)
    }

    @Transactional(readOnly = true)
    fun findRecentMissionsTitle(userId: Long): List<MissionTitleResponse> {
        val missions = missionQueryRepository.findTitleByUserIdLimit(userId = userId, limit = RECENT_LIMIT)
        return missions.map { MissionTitleResponse.from(it.title) }
    }

    @Transactional(readOnly = true)
    fun getDailyMissionDetail(dailyMissionId: Long, userId: Long): DailyMissionDetailResponse {
        val dailyMission =
            dailyMissionRepository.findByIdOrNull(dailyMissionId) ?: throw CustomNotFoundException("해당 id의 낫투두가 없습니다.")
        if (dailyMission.mission.userId != userId) throw CustomBadRequestException("사용자의 낫투두가 아닙니다.")
        val overlappedMissions = missionRepository.findByUserIdAndTitleAndSituation(
            userId = userId,
            title = dailyMission.mission.title,
            situation = dailyMission.mission.situation
        )
        val overlappedDailyMissionsCount =
            dailyMissionRepository.findAllByMissionIn(overlappedMissions).count { it.isCompleted() }
        return DailyMissionDetailResponse.of(
            dailyMission = dailyMission,
            sameMissionsCount = overlappedDailyMissionsCount
        )
    }

    @Transactional
    fun updateMission(dailyMissionId: Long, request: MissionUpdateRequest, userId: Long): Long {
        val dailyMission = dailyMissionRepository.findByIdOrNull(dailyMissionId) ?: throw CustomNotFoundException("해당 id의 낫투두가 없습니다.")
        if (dailyMission.mission.userId != userId) throw CustomBadRequestException("사용자의 낫투두가 아닙니다.")
        val mission = Mission.of(dailyMission.mission.id, request.title, request.situation, request.goal, dailyMission.mission.userId)
        missionRepository.save(mission)
        return dailyMissionId
    }

    @Transactional(readOnly = true)
    fun getTodayDailyMissions(date: LocalDate, userId: Long): List<DailyMissionResponse> {
        val todayDailyMissions = dailyMissionQueryRepository.fetchByDateAndUserId(date = date, userId = userId)
        return todayDailyMissions.map { DailyMissionResponse.from(it) }
    }

    @Transactional(readOnly = true)
    fun getWeeklyMissionCompletionRates(
        startDate: LocalDate,
        userId: Long
    ): List<DailyMissionCompletionStatusResponse> {
        DateUtil.validateSunday(startDate)
        val endDate = startDate.plusDays(6)
        val dailyMissions = dailyMissionQueryRepository.findByUserIdAndDatesBetween(
            userId = userId,
            startDate = startDate,
            endDate = endDate
        )
        val dateToDailyMissions = dailyMissions.groupBy { it.date }
        return DateUtil.getDatesBetween(startDate, endDate)
            .map { date -> calculateCompletionStatus(date, dateToDailyMissions[date]) }
    }

    @Transactional(readOnly = true)
    fun getMonthlyMissionCompletionRates(
        yearMonthInput: String,
        userId: Long
    ): List<DailyMissionCompletionStatusResponse> {
        val startDate = DateUtil.getFirstDayOfMonth(yearMonthInput)
        val endDate = DateUtil.getLastDayOfMonth(yearMonthInput)
        val dailyMissions = dailyMissionQueryRepository.findByUserIdAndDatesBetween(
            userId = userId,
            startDate = startDate,
            endDate = endDate
        )
        val dateToDailyMissions = dailyMissions.groupBy { it.date }
        return DateUtil.getDatesBetween(startDate, endDate)
            .map { date -> calculateCompletionStatus(date, dateToDailyMissions[date]) }
    }

    private fun calculateCompletionStatus(
        date: LocalDate,
        dailyMissions: List<DailyMission>?
    ): DailyMissionCompletionStatusResponse {
        return if (dailyMissions.isNullOrEmpty()) {
            DailyMissionCompletionStatusResponse(date, 0.0)
        } else {
            val percentage = dailyMissions.count { it.isCompleted() }.toDouble() / dailyMissions.size.toDouble()
            DailyMissionCompletionStatusResponse(date, percentage)
        }
    }

    @Transactional
    fun updateDailyMissionCompletionStatus(
        dailyMissionId: Long,
        request: DailyMissionUpdateCompletionStatusRequest,
        userId: Long
    ): DailyMissionResponse {
        val dailyMission = validateUserAndFindDailyMission(dailyMissionId = dailyMissionId, userId = userId)
        val updatedDailyMission =
            dailyMissionRepository.save(dailyMission.changeCompletionStatus(request.completionStatus))
        return DailyMissionResponse.from(updatedDailyMission)
    }

    @Transactional(readOnly = true)
    fun getDailyMissionPlanDates(dailyMissionId: Long, userId: Long): List<String> {
        val dailyMission = validateUserAndFindDailyMission(dailyMissionId = dailyMissionId, userId = userId)
        val sameDailyMissions = dailyMissionRepository.findAllByMission(dailyMission.mission)
        return sameDailyMissions.map { DateUtil.formatLocalDate(it.date) }
    }

    @Transactional
    fun deleteDailyMission(dailyMissionId: Long, userId: Long) {
        val dailyMission = validateUserAndFindDailyMission(dailyMissionId = dailyMissionId, userId = userId)
        dailyMissionRepository.delete(dailyMission)
    }

    @Transactional
    fun duplicateMission(dailyMissionId: Long, request: MissionDuplicateRequest, userId: Long) {
        validateDailyMissionsCount(userId = userId, dates = request.dates)
        val dailyMission = validateUserAndFindDailyMission(dailyMissionId = dailyMissionId, userId = userId)
        val sameDailyMissions = dailyMissionRepository.findAllByMission(dailyMission.mission)
        val overlappedDatesCount = sameDailyMissions.count { request.dates.contains(it.date) }
        if (overlappedDatesCount != 0) throw CustomBadRequestException("기존 낫투두와 날짜가 겹칩니다.")
        val dailyMissions = request.dates.map { DailyMission.of(dailyMission.mission, it) }
        dailyMissionRepository.saveAll(dailyMissions)
    }

    private fun validateUserAndFindDailyMission(dailyMissionId: Long, userId: Long): DailyMission {
        val dailyMission =
            dailyMissionRepository.findByIdOrNull(dailyMissionId) ?: throw CustomNotFoundException("해당 id의 낫투두가 없습니다.")
        if (dailyMission.mission.userId != userId) throw CustomBadRequestException("사용자의 낫투두가 아닙니다.")
        return dailyMission
    }

    private fun validateDailyMissionsCount(userId: Long, dates: List<LocalDate>) {
        val dailyMissions = dailyMissionRepository.findByUserIdAndDates(userId = userId, dates = dates)
        dailyMissions
            .groupBy { it.date }
            .forEach { (date, missions) ->
                if (missions.size > MAX_DAILY_MISSION_COUNT) {
                    throw CustomBadRequestException("낫투두는 <b>하루 최대 ${MAX_DAILY_MISSION_COUNT}개</b> 추가할 수 있어요.<br>꼭 개선할 것에만 집중해봐요:)")
                }
            }
    }

    companion object {
        const val MAX_DAILY_MISSION_COUNT = 3
        const val RECENT_LIMIT = 10L
    }
}

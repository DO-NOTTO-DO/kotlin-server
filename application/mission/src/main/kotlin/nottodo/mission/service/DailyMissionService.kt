package nottodo.mission.service

import nottodo.common.date.DateUtil
import nottodo.commonspring.exception.CustomBadRequestException
import nottodo.commonspring.exception.CustomNotFoundException
import nottodo.mission.request.DailyMissionUpdateCompletionStatusRequest
import nottodo.mission.response.DailyMissionCompletionStatusResponse
import nottodo.mission.response.DailyMissionResponse
import nottodo.persistence.rdb.domain.mission.entity.DailyMission
import nottodo.persistence.rdb.domain.mission.repository.DailyMissionQueryRepository
import nottodo.persistence.rdb.domain.mission.repository.DailyMissionRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class DailyMissionService(
    private val dailyMissionQueryRepository: DailyMissionQueryRepository,
    private val dailyMissionRepository: DailyMissionRepository,
) {

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
        val dailyMission =
            dailyMissionRepository.findByIdOrNull(dailyMissionId) ?: throw CustomNotFoundException("미션을 찾을 수 없습니다.")
        if (dailyMission.mission.userId != userId) throw CustomBadRequestException("사용자의 미션이 아닙니다.")
        val updatedDailyMission = dailyMissionRepository.save(dailyMission.changeCompletionStatus(request.completionStatus))
        return DailyMissionResponse.from(updatedDailyMission)
    }
}

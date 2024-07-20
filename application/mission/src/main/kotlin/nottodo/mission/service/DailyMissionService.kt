package nottodo.mission.service

import nottodo.mission.response.DailyMissionResponse
import nottodo.persistence.rdb.domain.mission.repository.DailyMissionQueryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class DailyMissionService(
    private val dailyMissionQueryRepository: DailyMissionQueryRepository
) {

    @Transactional(readOnly = true)
    fun getTodayDailyMissions(date: LocalDate, userId: Long): List<DailyMissionResponse> {
        val todayDailyMissions = dailyMissionQueryRepository.fetchByDateAndUserId(date = date, userId = userId)
        return todayDailyMissions.map { DailyMissionResponse.from(it) }
    }
}

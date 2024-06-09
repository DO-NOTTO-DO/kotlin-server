package nottodo.mission.service

import nottodo.common.converter.NonNullConverter
import nottodo.mission.response.DailyMissionResponse
import nottodo.persistence.rdb.domain.mission.repository.DailyMissionQueryRepository
import nottodo.persistence.rdb.domain.user.entity.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class DailyMissionService(
    private val dailyMissionQueryRepository: DailyMissionQueryRepository
) {

    @Transactional(readOnly = true)
    fun getTodayDailyMissions(date: LocalDate, user: User): List<DailyMissionResponse> {
        val todayDailyMissions = dailyMissionQueryRepository.fetchByDateAndUserId(date, NonNullConverter.convert(user.id))
        return todayDailyMissions.map { DailyMissionResponse.from(it) }
    }
}

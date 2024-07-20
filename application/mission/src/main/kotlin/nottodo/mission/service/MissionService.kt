package nottodo.mission.service

import nottodo.common.converter.NonNullConverter
import nottodo.commonspring.exception.CustomBadRequestException
import nottodo.mission.request.MissionCreateRequest
import nottodo.persistence.rdb.domain.mission.entity.Mission
import nottodo.persistence.rdb.domain.mission.repository.DailyMissionRepository
import nottodo.persistence.rdb.domain.mission.repository.MissionRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class MissionService(
    private val missionRepository: MissionRepository,
    private val dailyMissionRepository: DailyMissionRepository,
) {

    fun createMission(request: MissionCreateRequest, userId: Long): Long {
        val mission = Mission.of(
            title = request.title,
            situation = request.situation,
            goal = request.goal,
            userId = userId
        )
        validateDailyMissionsCount(userId = userId, dates = request.dates)
        return NonNullConverter.convert(missionRepository.save(mission).id)
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
    }
}

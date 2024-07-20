package nottodo.mission.service

import nottodo.common.converter.NonNullConverter
import nottodo.commonspring.exception.CustomBadRequestException
import nottodo.mission.request.MissionCreateRequest
import nottodo.mission.response.MissionTitleResponse
import nottodo.persistence.rdb.domain.mission.entity.DailyMission
import nottodo.persistence.rdb.domain.mission.entity.Mission
import nottodo.persistence.rdb.domain.mission.repository.DailyMissionRepository
import nottodo.persistence.rdb.domain.mission.repository.MissionQueryRepository
import nottodo.persistence.rdb.domain.mission.repository.MissionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class MissionService(
    private val missionRepository: MissionRepository,
    private val missionQueryRepository: MissionQueryRepository,
    private val dailyMissionRepository: DailyMissionRepository,
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
        val dailyMissions = request.dates.map { DailyMission.of(
            mission = savedMission,
            date = it,
        ) }
        dailyMissionRepository.saveAll(dailyMissions)
        return NonNullConverter.convert(mission.id)
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

    @Transactional(readOnly = true)
    fun findRecentMissionsTitle(userId: Long): List<MissionTitleResponse> {
        val missions = missionQueryRepository.findTitleByUserIdLimit(userId = userId, limit = 10)
        return missions.map { MissionTitleResponse.from(it.title) }
    }

    companion object {
        const val MAX_DAILY_MISSION_COUNT = 3
        const val RECENT_LIMIT = 10
    }
}

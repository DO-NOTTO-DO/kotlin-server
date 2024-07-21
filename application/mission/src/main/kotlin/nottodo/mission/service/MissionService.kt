package nottodo.mission.service

import nottodo.common.converter.NonNullConverter
import nottodo.commonspring.exception.CustomBadRequestException
import nottodo.commonspring.exception.CustomNotFoundException
import nottodo.mission.request.MissionCreateRequest
import nottodo.mission.response.DailyMissionDetailResponse
import nottodo.mission.response.MissionTitleResponse
import nottodo.persistence.rdb.domain.mission.entity.DailyMission
import nottodo.persistence.rdb.domain.mission.entity.Mission
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

    companion object {
        const val MAX_DAILY_MISSION_COUNT = 3
        const val RECENT_LIMIT = 10L
    }
}

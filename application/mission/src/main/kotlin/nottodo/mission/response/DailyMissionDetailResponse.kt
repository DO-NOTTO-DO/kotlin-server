package nottodo.mission.response

import nottodo.common.converter.NonNullConverter
import nottodo.persistence.rdb.domain.mission.entity.DailyMission

data class DailyMissionDetailResponse(
    val id: Long,
    val title: String,
    val situation: String,
    val goal: String,
    val count: Int
) {
    companion object {
        fun of(dailyMission: DailyMission, sameMissionsCount: Int): DailyMissionDetailResponse {
            return DailyMissionDetailResponse(
                id = NonNullConverter.convert(dailyMission.id),
                title = dailyMission.mission.title,
                situation = dailyMission.mission.situation,
                goal = dailyMission.mission.goal,
                count = sameMissionsCount
            )
        }
    }
}

package nottodo.mission.controller

object MissionControllerPath {
    const val GET_DAILY_MISSIONS = "api/v1/mission/daily/{date}"
    const val CREATE_MISSION = "api/v1/mission"
    const val GET_WEEKLY_COMPLETION_STATE = "api/v1/mission/week/{startDate}"
    const val UPDATE_DAILY_MISSION_COMPLETION_STATUS = "api/v1/mission/{dailyMissionId}/check"
    const val GET_DAILY_MISSION = "api/v1/mission/{dailyMissionId}"
    const val GET_RECENT_MISSIONS = "api/v1/mission/recent"
}

package nottodo.mission.response

data class MissionTitleResponse(
    val title: String
) {
    companion object {
        fun from(missionTitle: String): MissionTitleResponse {
            return MissionTitleResponse(missionTitle)
        }
    }
}

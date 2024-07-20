package nottodo.mission.response

import com.fasterxml.jackson.annotation.JsonFormat
import nottodo.common.converter.NonNullConverter
import nottodo.persistence.rdb.domain.mission.entity.CompletionStatus
import nottodo.persistence.rdb.domain.mission.entity.DailyMission
import java.time.LocalDate

data class DailyMissionResponse(
    val id: Long,
    val title: String,
    val situationName: String,
    val completionStatus: CompletionStatus,
    @JsonFormat(pattern = "yyyy-MM-dd")
    val date: LocalDate
) {
    companion object {
        fun from(dailyMission: DailyMission): DailyMissionResponse {
            return DailyMissionResponse(
                id = NonNullConverter.convert(dailyMission.id),
                title = dailyMission.mission.title,
                situationName = dailyMission.mission.situation,
                completionStatus = dailyMission.completionStatus,
                date = dailyMission.date
            )
        }
    }
}

package nottodo.mission.response

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate

data class DailyMissionCompletionStatusResponse(
    @JsonFormat(pattern = "yyyy-MM-dd")
    val actionDate: LocalDate,
    val percentage: Double
) {
    companion object {
        fun of(actionDate: LocalDate, percentage: Double): DailyMissionCompletionStatusResponse {
            return DailyMissionCompletionStatusResponse(
                actionDate = actionDate,
                percentage = percentage
            )
        }
    }
}

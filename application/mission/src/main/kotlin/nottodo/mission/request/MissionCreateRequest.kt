package nottodo.mission.request

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate

data class MissionCreateRequest(
    val title: String,
    val situation: String,
    val goal: String,
    val actions: List<String>,
    @JsonFormat(pattern = "yyyy.MM.dd")
    val dates: List<LocalDate>
)

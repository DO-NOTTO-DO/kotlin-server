package nottodo.mission.request

import java.time.LocalDate

data class MissionCreateRequest(
    val title: String,
    val situation: String,
    val goal: String,
    val actions: List<String>,
    val dates: List<LocalDate>
)

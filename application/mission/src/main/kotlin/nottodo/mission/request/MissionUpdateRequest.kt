package nottodo.mission.request

import jakarta.validation.constraints.NotNull

data class MissionUpdateRequest(
    @field:NotNull
    val title: String,
    @field:NotNull
    val situation: String,
    val goal: String?,
)

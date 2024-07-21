package nottodo.mission.request

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.time.LocalDate

data class MissionCreateRequest(
    @field:NotNull
    val title: String,
    @field:NotNull
    val situation: String,
    val goal: String?,
    @field:NotEmpty(message = "날짜가 존재하지 않습니다.")
    @JsonFormat(pattern = "yyyy.MM.dd")
    val dates: List<LocalDate>
)

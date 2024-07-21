package nottodo.mission.request

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.validation.constraints.NotEmpty
import java.time.LocalDate

data class MissionDuplicateRequest(
    @field:NotEmpty(message = "날짜가 존재하지 않습니다.")
    @JsonFormat(pattern = "yyyy.MM.dd")
    val dates: List<LocalDate>
)

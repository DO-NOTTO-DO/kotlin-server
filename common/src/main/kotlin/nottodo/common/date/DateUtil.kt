package nottodo.common.date

import java.time.DayOfWeek
import java.time.LocalDate

object DateUtil {

    fun validateSunday(date: LocalDate) {
        if (date.dayOfWeek != DayOfWeek.SUNDAY) throw IllegalArgumentException("일요일이 아닙니다.")
    }

    fun getDatesBetween(start: LocalDate, end: LocalDate): List<LocalDate> {
        return generateSequence(start) { date ->
            if (date.isBefore(end)) date.plusDays(1) else null
        }.toList()
    }
}

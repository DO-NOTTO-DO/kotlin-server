package nottodo.common.date

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeParseException

object DateUtil {

    fun validateSunday(date: LocalDate) {
        if (date.dayOfWeek != DayOfWeek.SUNDAY) throw IllegalArgumentException("일요일이 아닙니다.")
    }

    fun getDatesBetween(start: LocalDate, end: LocalDate): List<LocalDate> {
        return generateSequence(start) { date ->
            if (date.isBefore(end)) date.plusDays(1) else null
        }.toList()
    }

    fun getFirstDayOfMonth(yearMonth: String): LocalDate {
        try {
            return YearMonth.parse(yearMonth).atDay(1)
        } catch (e: DateTimeParseException) {
            throw IllegalArgumentException("월의 형식은 'yyyy-MM' 형태여야 합니다.")
        }
    }

    fun getLastDayOfMonth(yearMonth: String): LocalDate {
        try {
            return YearMonth.parse(yearMonth).atEndOfMonth()
        } catch (e: DateTimeParseException) {
            throw IllegalArgumentException("월의 형식은 'yyyy-MM' 형태여야 합니다.")
        }
    }
}

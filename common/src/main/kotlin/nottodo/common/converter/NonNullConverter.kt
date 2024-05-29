package nottodo.common.converter

object NonNullConverter {
    fun <T> convert(t: T?): T {
        return t ?: throw IllegalArgumentException("Null 값 예외가 발생했습니다.")
    }
}

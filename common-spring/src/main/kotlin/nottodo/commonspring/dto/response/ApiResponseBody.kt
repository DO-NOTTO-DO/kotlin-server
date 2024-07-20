package nottodo.commonspring.dto.response

data class ApiResponseBody<T>(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: T?
) {

    companion object {
        fun <T> success(status: Int, message: String, data: T?): ApiResponseBody<T> {
            return ApiResponseBody(status, true, message, data)
        }

        fun <T> error(status: Int, message: String): ApiResponseBody<T> {
            return ApiResponseBody(status, false, message, null)
        }
    }
}

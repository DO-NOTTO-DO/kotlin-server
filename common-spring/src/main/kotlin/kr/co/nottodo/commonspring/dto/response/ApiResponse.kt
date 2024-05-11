package kr.co.nottodo.commonspring.dto.response

data class ApiResponse<T>(
    val status: Int,
    val success: Boolean,
    val message: String
) {
}

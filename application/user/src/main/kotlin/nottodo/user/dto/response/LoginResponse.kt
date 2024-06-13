package nottodo.user.dto.response

data class LoginResponse(
    val accessToken: String,
    val userId: Long
) {
    companion object {
        fun of(accessToken: String, userId: Long) = LoginResponse(accessToken, userId)
    }
}

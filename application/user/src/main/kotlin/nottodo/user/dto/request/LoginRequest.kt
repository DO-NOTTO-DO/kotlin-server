package nottodo.user.dto.request

data class LoginRequest(
    val socialToken: String,
    val fcmToken: String,
    val name: String? = null,
    val email: String? = null
)

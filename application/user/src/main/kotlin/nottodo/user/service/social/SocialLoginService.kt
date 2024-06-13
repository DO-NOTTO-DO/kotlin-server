package nottodo.user.service.social

import nottodo.user.dto.request.LoginRequest
import nottodo.user.dto.response.LoginResponse

interface SocialLoginService {
    fun login(request: LoginRequest): LoginResponse
}

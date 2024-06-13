package nottodo.user.service.social

import nottodo.user.dto.SocialInfo
import nottodo.user.dto.request.LoginRequest

interface SocialLoginService {
    fun login(request: LoginRequest): SocialInfo
}

package nottodo.user.service.social

import nottodo.user.dto.SocialInfoDto
import nottodo.user.dto.request.LoginRequest

interface SocialLoginService {
    fun login(request: LoginRequest): SocialInfoDto
}

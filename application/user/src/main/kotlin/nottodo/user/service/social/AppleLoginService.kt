package nottodo.user.service.social

import nottodo.user.dto.SocialInfoDto
import nottodo.user.dto.request.LoginRequest
import org.springframework.stereotype.Service

@Service
class AppleLoginService: SocialLoginService {
    override fun login(request: LoginRequest): SocialInfoDto {
        TODO("Not yet implemented")
    }
}

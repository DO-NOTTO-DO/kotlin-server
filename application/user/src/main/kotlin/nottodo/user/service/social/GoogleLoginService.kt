package nottodo.user.service.social

import nottodo.user.dto.SocialInfo
import nottodo.user.dto.request.LoginRequest
import org.springframework.stereotype.Service

@Service
class GoogleLoginService: SocialLoginService {
    override fun login(request: LoginRequest): SocialInfo {
        TODO("Not yet implemented")
    }
}

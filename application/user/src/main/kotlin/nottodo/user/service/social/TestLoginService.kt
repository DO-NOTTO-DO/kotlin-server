package nottodo.user.service.social

import nottodo.user.dto.request.LoginRequest
import nottodo.user.dto.response.LoginResponse
import org.springframework.stereotype.Service

@Service
class TestLoginService: SocialLoginService {
    override fun login(request: LoginRequest): LoginResponse {
        TODO("Not yet implemented")
    }
}

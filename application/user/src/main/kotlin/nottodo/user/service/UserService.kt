package nottodo.user.service

import nottodo.persistence.rdb.domain.user.entity.SocialType
import nottodo.user.dto.request.LoginRequest
import nottodo.user.dto.response.LoginResponse
import nottodo.user.service.social.factory.SocialLoginServiceFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val socialLoginServiceFactory: SocialLoginServiceFactory
) {

    @Transactional
    fun login(socialType: SocialType, request: LoginRequest): LoginResponse {
        val service = socialLoginServiceFactory.getServiceFrom(socialType)
        return service.login(request)
    }
}


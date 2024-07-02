package nottodo.user.service.social

import nottodo.commonspring.exception.CustomBadRequestException
import nottodo.persistence.rdb.domain.user.entity.SocialType
import nottodo.user.dto.SocialInfoDto
import nottodo.user.dto.request.LoginRequest
import org.springframework.stereotype.Service

@Service
class TestLoginService: SocialLoginService {
    override fun login(request: LoginRequest): SocialInfoDto {
        return SocialInfoDto(
            email = request.email ?: throw CustomBadRequestException("TEST 로그인 시에는 email이 필요합니다."),
            socialType = SocialType.TEST,
            socialId = request.email,
            name = "TEST"
        )
    }
}

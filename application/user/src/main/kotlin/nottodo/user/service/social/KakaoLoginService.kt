package nottodo.user.service.social

import nottodo.infra.kakao.service.KakaoUserService
import nottodo.persistence.rdb.domain.user.entity.SocialType
import nottodo.user.dto.SocialInfo
import nottodo.user.dto.request.LoginRequest
import org.springframework.stereotype.Service

@Service
class KakaoLoginService(
    private val kakaoUserService: KakaoUserService
) : SocialLoginService {
    override fun login(request: LoginRequest): SocialInfo {
        val kakaoUserInfo = kakaoUserService.getUserInfo(request.socialToken)
        return SocialInfo(
            email = kakaoUserInfo.kakaoAccount.email ?: "-",
            socialType = SocialType.KAKAO,
            socialId = kakaoUserInfo.id,
            name = kakaoUserInfo.properties.nickname
        )
    }
}

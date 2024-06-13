package nottodo.user.service.social.factory

import nottodo.persistence.rdb.domain.user.entity.SocialType
import nottodo.user.service.UserService
import nottodo.user.service.social.*
import org.springframework.stereotype.Component

@Component
class SocialLoginServiceFactory(
    private val appleLoginService: AppleLoginService,
    private val googleLoginService: GoogleLoginService,
    private val kakaoLoginService: KakaoLoginService,
    private val testLoginService: TestLoginService
) {
    fun getServiceFrom(socialType: SocialType): SocialLoginService {
        return when (socialType) {
            SocialType.APPLE -> appleLoginService
            SocialType.GOOGLE -> googleLoginService
            SocialType.KAKAO -> kakaoLoginService
            SocialType.TEST -> testLoginService
        }
    }
}

package nottodo.api.domain.user.service

import nottodo.api.config.security.jwt.JwtTokenProvider
import nottodo.api.config.security.jwt.dto.EmailAndSocialType
import nottodo.persistence.rdb.domain.user.entity.SocialType
import nottodo.user.dto.request.LoginRequest
import nottodo.user.dto.response.LoginResponse
import nottodo.user.service.FcmTokenService
import nottodo.user.service.UserService
import nottodo.user.service.social.factory.SocialLoginServiceFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LoginService(
    private val jwtTokenProvider: JwtTokenProvider,
    private val userService: UserService,
    private val fcmTokenService: FcmTokenService,
    private val userDetailsService: CustomUserDetailsService,
    private val socialLoginServiceFactory: SocialLoginServiceFactory
) {

    @Transactional
    fun login(socialType: SocialType, request: LoginRequest): LoginResponse {
        val service = socialLoginServiceFactory.getServiceFrom(socialType)
        val socialInfo = service.login(request)
        val userDto = userService.signupOrLogin(socialInfo)
        fcmTokenService.findByTokenOrCreate(userDto.id, request.fcmToken)
        val accessToken = jwtTokenProvider.generateAccessToken(socialInfo.email, socialType)
        userDetailsService.loadUserByEmailAndSocialType(EmailAndSocialType(userDto.email, userDto.socialType))
        return LoginResponse.of(accessToken, userDto.id)
    }
}

package nottodo.api.domain.user.service

import nottodo.api.config.security.CustomUserDetails
import nottodo.api.config.security.jwt.dto.EmailAndSocialType
import nottodo.persistence.rdb.domain.user.repository.UserAuthenticationRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val userAuthenticationRepository: UserAuthenticationRepository
) : UserDetailsService {

    @Deprecated("loadUserByEmailAndSocialType 메소드를 이용해주세요.")
    override fun loadUserByUsername(username: String): UserDetails {
        throw UnsupportedOperationException("시큐리티 오류입니다.")
    }

    fun loadUserByEmailAndSocialType(emailAndSocialType: EmailAndSocialType): CustomUserDetails {
        val user = userAuthenticationRepository.findByEmailAndSocialType(
            emailAndSocialType.email,
            emailAndSocialType.socialType
        )
        return CustomUserDetails(user)
    }
}

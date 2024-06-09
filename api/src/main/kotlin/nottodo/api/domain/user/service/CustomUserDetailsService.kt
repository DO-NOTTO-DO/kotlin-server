package nottodo.api.domain.user.service

import nottodo.api.config.security.jwt.dto.EmailAndSocialType
import nottodo.persistence.rdb.domain.user.entity.SocialType
import nottodo.persistence.rdb.domain.user.repository.UserAuthenticationRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
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

    fun loadUserByEmailAndSocialType(emailAndSocialType: EmailAndSocialType): UserDetails {
        val user = userAuthenticationRepository.findByEmailAndSocialType(
            emailAndSocialType.email,
            emailAndSocialType.socialType
        )
        val authorities = listOf(SimpleGrantedAuthority(user.role.name))
        return User.withUsername(user.email)
            .password(null)
            .authorities(authorities)
            .accountExpired(false)
            .accountLocked(false)
            .credentialsExpired(false)
            .disabled(false)
            .build()
    }
}

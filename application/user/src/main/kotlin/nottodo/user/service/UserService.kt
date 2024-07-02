package nottodo.user.service

import nottodo.persistence.rdb.domain.user.entity.User
import nottodo.persistence.rdb.domain.user.repository.UserRepository
import nottodo.user.dto.SocialInfoDto
import nottodo.user.dto.UserDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
) {

    @Transactional
    fun signupOrLogin(socialInfoDto: SocialInfoDto): UserDto {
        val user = userRepository.findByEmailAndSocialType(socialInfoDto.email, socialInfoDto.socialType)
            ?: userRepository.save(
                User.of(
                    socialId = socialInfoDto.socialId,
                    email = socialInfoDto.email,
                    socialType = socialInfoDto.socialType,
                    name = socialInfoDto.name,
                    image = DEFAULT_USER_IMAGE,
                )
            )
        return UserDto.from(user)
    }

    companion object {
        const val DEFAULT_USER_IMAGE = "https://cdn-icons-png.flaticon.com/512/847/847969.png"
    }
}


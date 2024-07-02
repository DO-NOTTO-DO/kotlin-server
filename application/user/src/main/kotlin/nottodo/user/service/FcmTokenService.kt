package nottodo.user.service

import nottodo.commonspring.exception.CustomBadRequestException
import nottodo.persistence.rdb.domain.user.entity.FcmToken
import nottodo.persistence.rdb.domain.user.repository.FcmTokenRepository
import nottodo.persistence.rdb.domain.user.repository.UserRepository
import nottodo.user.dto.FcmTokenDto
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FcmTokenService(
    private val fcmTokenRepository: FcmTokenRepository,
    private val userRepository: UserRepository
) {

    @Transactional
    fun findByTokenOrCreate(userId: Long, token: String): FcmTokenDto {
        val user = userRepository.findByIdOrNull(userId) ?: throw CustomBadRequestException("유저를 찾을 수 없습니다.")
        val fcmToken = fcmTokenRepository.findByToken(token) ?: fcmTokenRepository.save(FcmToken.of(user, token))
        return FcmTokenDto.of(fcmToken, user)
    }
}

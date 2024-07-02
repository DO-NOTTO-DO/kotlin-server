package nottodo.user.dto

import nottodo.common.converter.NonNullConverter
import nottodo.persistence.rdb.domain.user.entity.FcmToken
import nottodo.persistence.rdb.domain.user.entity.User

data class FcmTokenDto(
    val id: Long,
    val token: String,
    val user: UserDto
) {
    companion object {
        fun of(fcmToken: FcmToken, user: User): FcmTokenDto {
            return FcmTokenDto(
                id = NonNullConverter.convert(fcmToken.id),
                token = fcmToken.token,
                user = UserDto.from(user)
            )
        }
    }
}

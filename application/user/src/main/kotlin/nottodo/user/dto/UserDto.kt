package nottodo.user.dto

import nottodo.common.converter.NonNullConverter
import nottodo.persistence.rdb.domain.user.entity.SocialType
import nottodo.persistence.rdb.domain.user.entity.User
import nottodo.persistence.rdb.domain.user.entity.UserRole

data class UserDto(
    val id: Long,
    val email: String,
    val socialId: String,
    val socialType : SocialType,
    val name: String?,
    val image: String,
    val role: UserRole
) {
    companion object {
        fun from(user: User): UserDto {
            return UserDto(
                id = NonNullConverter.convert(user.id),
                email = user.email,
                socialId = user.socialId,
                socialType = user.socialType,
                name = user.name,
                image = user.image,
                role = user.role
            )
        }
    }
}

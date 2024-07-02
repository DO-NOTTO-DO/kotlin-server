package nottodo.user.dto

import nottodo.persistence.rdb.domain.user.entity.SocialType

data class SocialInfoDto(
    val email: String,
    val socialType: SocialType,
    val socialId: String,
    val name: String
)

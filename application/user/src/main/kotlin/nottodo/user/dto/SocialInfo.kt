package nottodo.user.dto

import nottodo.persistence.rdb.domain.user.entity.SocialType

data class SocialInfo(
    val email: String,
    val socialType: SocialType,
    val socialId: String,
    val name: String
)

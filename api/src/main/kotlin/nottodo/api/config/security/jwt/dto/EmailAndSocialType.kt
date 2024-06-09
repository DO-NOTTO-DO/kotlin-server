package nottodo.api.config.security.jwt.dto

import nottodo.persistence.rdb.domain.user.entity.SocialType

data class EmailAndSocialType(
    val email: String,
    val socialType: SocialType
)

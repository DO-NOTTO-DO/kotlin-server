package nottodo.api.config.security.jwt

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "jwt")
class JwtProperties(
    val accessSecret: String,
    val accessExpireLength: Long
)

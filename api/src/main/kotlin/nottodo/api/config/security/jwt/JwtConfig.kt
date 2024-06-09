package nottodo.api.config.security.jwt

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(value = [JwtProperties::class])
class JwtConfig {
}

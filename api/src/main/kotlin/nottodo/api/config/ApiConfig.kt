package nottodo.api.config

import nottodo.api.config.security.jwt.JwtConfig
import nottodo.persistence.rdb.config.JpaConfig
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@ComponentScan("nottodo")
@Import(
    value = [
        JpaConfig::class,
        JwtConfig::class,
    ]
)
@Configuration
class ApiConfig {
}

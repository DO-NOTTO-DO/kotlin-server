package nottodo.api.config

import nottodo.api.config.security.jwt.JwtConfig
import nottodo.mission.config.MissionConfig
import nottodo.persistence.rdb.config.JpaConfig
import nottodo.quote.config.QuoteConfig
import nottodo.recommend.config.RecommendConfig
import nottodo.user.config.UserConfig
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@ComponentScan("nottodo.api")
@Import(
    value = [
        JpaConfig::class,
        JwtConfig::class,
        MissionConfig::class,
        QuoteConfig::class,
        RecommendConfig::class,
        UserConfig::class,
    ]
)
@Configuration
class ApiConfig {
}

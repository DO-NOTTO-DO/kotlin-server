package nottodo.recommend.config

import nottodo.persistence.rdb.config.JpaConfig
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@ComponentScan("nottodo.recommend")
@Import(
    value = [
        JpaConfig::class,
    ]
)
@Configuration
class RecommendConfig {
}

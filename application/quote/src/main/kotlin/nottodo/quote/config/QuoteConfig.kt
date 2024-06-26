package nottodo.quote.config

import nottodo.persistence.rdb.config.JpaConfig
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@ComponentScan("nottodo.quote")
@Import(
    value = [
        JpaConfig::class
    ]
)
@Configuration
class QuoteConfig {
}

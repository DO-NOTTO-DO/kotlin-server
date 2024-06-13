package nottodo.user.config

import nottodo.infra.kakao.config.KakaoConfig
import nottodo.persistence.rdb.config.JpaConfig
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@ComponentScan("nottodo.user")
@Import(
    value = [
        JpaConfig::class,
        KakaoConfig::class
    ]
)
@Configuration
class UserConfig {
}

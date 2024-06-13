package nottodo.infra.kakao.config

import nottodo.persistence.rdb.config.JpaConfig
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@ComponentScan("nottodo.infra.kakao")
@Import(
    value = [
        JpaConfig::class,
    ]
)
@EnableFeignClients(basePackages = ["nottodo.infra.kakao"])
@Configuration
open class KakaoConfig {
}

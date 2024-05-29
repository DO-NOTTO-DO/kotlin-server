package nottodo.persistence.rdb.config

import jakarta.annotation.PostConstruct
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import java.util.*

@EntityScan(
    value = ["nottodo.persistence.rdb.domain"]
)
@EnableJpaRepositories(
    value = ["nottodo.persistence.rdb.domain"]
)
@ComponentScan("nottodo.persistence.rdb")
@Configuration
class JpaConfig {

    @PostConstruct
    fun setTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"))
    }
}

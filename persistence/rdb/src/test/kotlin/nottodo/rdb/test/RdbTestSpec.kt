package nottodo.rdb.test

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import nottodo.persistence.rdb.config.JpaConfig
import nottodo.persistence.rdb.config.querydsl.QuerydslConfig
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration

@DataJpaTest
@ActiveProfiles("test")
@EnableAutoConfiguration
@ContextConfiguration(classes = [JpaConfig::class, QuerydslConfig::class])
abstract class RdbTestSpec(body: DescribeSpec.() -> Unit = {}) : DescribeSpec(body) {

    override fun extensions() = listOf(SpringExtension)
}

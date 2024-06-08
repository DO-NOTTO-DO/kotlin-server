package nottodo.recommend.test

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import nottodo.recommend.config.RecommendConfig
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(classes = [RecommendConfig::class])
@ActiveProfiles("test")
@EnableAutoConfiguration
@Import(RecommendConfig::class)
abstract class RecommendTestSpec(body: DescribeSpec.() -> Unit = {}) : DescribeSpec(body) {

    override fun extensions() = listOf(SpringExtension)
}

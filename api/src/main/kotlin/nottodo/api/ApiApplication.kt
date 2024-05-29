package nottodo.api

import nottodo.api.config.ApiConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@Import(ApiConfig::class)
@SpringBootApplication(scanBasePackages = ["nottodo"])
class ApiApplication

fun main(args: Array<String>) {
    runApplication<ApiApplication>(*args)
}

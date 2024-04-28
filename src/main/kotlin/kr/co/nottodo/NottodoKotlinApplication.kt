package kr.co.nottodo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NottodoKotlinApplication

fun main(args: Array<String>) {
    runApplication<NottodoKotlinApplication>(*args)
}

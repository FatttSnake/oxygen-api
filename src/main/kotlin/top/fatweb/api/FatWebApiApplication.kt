package top.fatweb.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
class FatWebApiApplication

fun main(args: Array<String>) {
    runApplication<FatWebApiApplication>(*args)
}

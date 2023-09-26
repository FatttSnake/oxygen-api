package top.fatweb.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FatWebApiApplication

fun main(args: Array<String>) {
    runApplication<FatWebApiApplication>(*args)
}

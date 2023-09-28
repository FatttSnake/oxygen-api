package top.fatweb.api

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.io.File

@SpringBootApplication
@EnableTransactionManagement
class FatWebApiApplication

fun main(args: Array<String>) {
    val logger = LoggerFactory.getLogger("main")

    if (File("application-config.yml").exists()) {
        runApplication<FatWebApiApplication>(*args)
    } else {
        logger.warn("File ‘application.yml’ cannot be found in the running path. The configuration file template 'application.example.yml' has been created. Please change the configuration file content and rename it to 'application.yml', and then restart the server.")
       FatWebApiApplication::class.java.getResource("/application-config-template.yml")?.readText()
            ?.let { File("application-config.example.yml").writeText(it) }
    }
}

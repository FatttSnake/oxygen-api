package top.fatweb.api

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.io.File
import java.util.*

/**
 * Application main class
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@SpringBootApplication
@EnableTransactionManagement
class FatWebApiApplication

/**
 * Main function
 *
 * @param args
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
fun main(args: Array<String>) {
    val logger = LoggerFactory.getLogger("main")

    if (!File("data").isDirectory) {
        if (!File("data").mkdir()) {
            logger.error("Can not create directory 'data', please try again later.")
            return
        }
    }

    if (File("application-config.yml").exists() || File("data/application-config.yml").exists()) {
        runApplication<FatWebApiApplication>(*args)
    } else {
        logger.warn("File 'application.yml' cannot be found in data path. The configuration file template 'application.example.yml' has been created in directory 'data'. Please change the configuration file content, rename it to 'application.yml', and then restart the server.")
        FatWebApiApplication::class.java.getResource("/application-config-template.yml")?.readText()?.let {
            File("data/application-config.example.yml").writeText(
                it.replace(
                    "\$uuid\$", UUID.randomUUID().toString().replace("-", "")
                )
            )
        }
    }
}

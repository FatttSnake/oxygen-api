package top.fatweb.api.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import top.fatweb.api.properties.ServerProperties

/**
 * Swagger API doc configuration
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Configuration
class SwaggerConfig {

    @Bean
    fun customOpenAPI(): OpenAPI? {
        val contact = Contact().name("FatttSnake").url("https://fatweb.top").email("fatttsnake@fatweb.top")
        return OpenAPI().info(
            Info().title("FatWeb API 文档").description("FatWeb 后端 API 文档，包含各个 Controller 调用信息")
                .contact(contact).version(
                    ServerProperties.version
                )
        )
    }
}
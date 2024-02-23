package top.fatweb.oxygen.api.config

import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import top.fatweb.oxygen.api.annotation.ApiController

/**
 * Web MVC configurer configuration
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see WebMvcRegistrations
 */
@Configuration
class WebMvcConfigurerConfig : WebMvcConfigurer {
    override fun configurePathMatch(configurer: PathMatchConfigurer) {
        configurer.addPathPrefix("/api/{API_VERSION}") { it.isAnnotationPresent(ApiController::class.java) }
    }
}
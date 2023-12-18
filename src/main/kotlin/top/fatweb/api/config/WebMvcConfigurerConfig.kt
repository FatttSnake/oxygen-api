package top.fatweb.api.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import top.fatweb.api.annotation.ApiController

@Configuration
class WebMvcConfigurerConfig : WebMvcConfigurer {
    override fun configurePathMatch(configurer: PathMatchConfigurer) {
        configurer.addPathPrefix("/api/{API_VERSION}") {it.isAnnotationPresent(ApiController::class.java)}
    }
}
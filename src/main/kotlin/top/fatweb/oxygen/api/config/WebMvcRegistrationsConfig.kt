package top.fatweb.oxygen.api.config

import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
import top.fatweb.oxygen.api.util.ApiResponseMappingHandlerMapping

/**
 * Web MVC registrations configuration
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see WebMvcRegistrations
 */
@Configuration
class WebMvcRegistrationsConfig : WebMvcRegistrations {
    override fun getRequestMappingHandlerMapping(): RequestMappingHandlerMapping = ApiResponseMappingHandlerMapping()
}
package top.fatweb.oxygen.api.config

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import top.fatweb.oxygen.api.filter.ExceptionFilter

/**
 * Filter configuration
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Configuration
class FilterConfig {
    @Bean
    fun exceptionFilterRegistrationBean(exceptionFilter: ExceptionFilter): FilterRegistrationBean<ExceptionFilter> =
        FilterRegistrationBean(exceptionFilter).apply {
            setBeanName("exceptionFilter")
            order = -100
        }
}
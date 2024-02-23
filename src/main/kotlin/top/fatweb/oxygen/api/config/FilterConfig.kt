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
    fun exceptionFilterRegistrationBean(exceptionFilter: ExceptionFilter): FilterRegistrationBean<ExceptionFilter> {
        val registrationBean = FilterRegistrationBean(exceptionFilter)
        registrationBean.setBeanName("exceptionFilter")
        registrationBean.order = -100

        return registrationBean
    }
}
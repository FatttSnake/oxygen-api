package top.fatweb.api.config

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import top.fatweb.api.filter.ExceptionFilter

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
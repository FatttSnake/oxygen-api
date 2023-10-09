package top.fatweb.api.config

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MybatisPlusConfig {
    @Bean
    fun mybatisPlusInterceptor(): MybatisPlusInterceptor {
        val mybatisPlusInterceptor = MybatisPlusInterceptor()
        mybatisPlusInterceptor.addInnerInterceptor(OptimisticLockerInnerInterceptor())
        mybatisPlusInterceptor.addInnerInterceptor(PaginationInnerInterceptor())

        return mybatisPlusInterceptor
    }

}
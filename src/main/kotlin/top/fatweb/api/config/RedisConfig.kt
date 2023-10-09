package top.fatweb.api.config

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisConfig {
    @Bean
    fun redisTemplate(redisConnectionFactory: RedisConnectionFactory): RedisTemplate<*, *> {
        val redisTemplate = RedisTemplate<String, Any>()
        redisTemplate.connectionFactory = redisConnectionFactory
        val stringRedisSerializer = StringRedisSerializer()
        val objectMapper = ObjectMapper().registerModules(JavaTimeModule()).apply {
            setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
            activateDefaultTyping(
                this.polymorphicTypeValidator, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY
            )
        }
        val anyJackson2JsonRedisSerializer = Jackson2JsonRedisSerializer(objectMapper, Any::class.java)

        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        redisTemplate.keySerializer = stringRedisSerializer
        redisTemplate.valueSerializer = anyJackson2JsonRedisSerializer

        // Hash的key也采用StringRedisSerializer的序列化方式
        redisTemplate.hashKeySerializer = stringRedisSerializer
        redisTemplate.hashValueSerializer = anyJackson2JsonRedisSerializer

        redisTemplate.afterPropertiesSet()

        return redisTemplate
    }
}
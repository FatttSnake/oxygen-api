package top.fatweb.oxygen.api.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.boot.jackson.JsonComponent
import org.springframework.context.annotation.Bean
import retrofit2.converter.jackson.JacksonConverterFactory
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Jackson configuration
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@JsonComponent
class JacksonConfig {
    /**
     * The format of the time in response when request APIs
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     */
    @set:Value($$"${spring.jackson.date-format}")
    lateinit var dateFormat: String

    /**
     * The timezone of the time in response when request APIs
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     * @see TimeZone
     */
    @set:Value($$"${spring.jackson.time-zone}}")
    lateinit var timeZone: TimeZone

    @Bean
    fun jackson2ObjectMapperBuilderCustomizer() = Jackson2ObjectMapperBuilderCustomizer { builder ->
        val simpleDateFormat = SimpleDateFormat(dateFormat)
        simpleDateFormat.timeZone = timeZone
        val localDateTimeSerializer = LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateFormat))

        builder
            .failOnEmptyBeans(false)
            .failOnUnknownProperties(false)
            .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .featuresToDisable(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE)
            .dateFormat(simpleDateFormat)
            .serializerByType(LocalDateTime::class.java, localDateTimeSerializer)
            .modules(
                JavaTimeModule(),
                KotlinModule.Builder().build()
            )
    }

    @Bean
    fun jacksonConverterFactory(objectMapper: ObjectMapper): JacksonConverterFactory =
        JacksonConverterFactory.create(objectMapper)
}

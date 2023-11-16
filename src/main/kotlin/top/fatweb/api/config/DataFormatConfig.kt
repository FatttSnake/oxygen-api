package top.fatweb.api.config

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.boot.jackson.JsonComponent
import org.springframework.context.annotation.Bean
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Data format config
 *
 * @author FatttSnake
 * @since 1.0.0
 */
@JsonComponent
class DataFormatConfig {
    @set:Value("\${spring.jackson.date-format}")
    lateinit var dataFormat: String

    @set:Value("\${spring.jackson.time-zone}}")
    lateinit var timeZone: TimeZone

    @Bean
    fun jackson2ObjectMapperBuilder() = Jackson2ObjectMapperBuilderCustomizer { builder: Jackson2ObjectMapperBuilder ->
        val tz = timeZone
        val df: DateFormat = SimpleDateFormat(dataFormat)
        df.timeZone = tz
        builder.failOnEmptyBeans(false).failOnUnknownProperties(false)
            .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).dateFormat(df)
    }


    @Bean
    fun jackson2ObjectMapperBuilderCustomizer() =
        Jackson2ObjectMapperBuilderCustomizer { builder: Jackson2ObjectMapperBuilder ->
            builder.serializerByType(
                LocalDateTime::class.java, LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dataFormat))
            )
        }

}
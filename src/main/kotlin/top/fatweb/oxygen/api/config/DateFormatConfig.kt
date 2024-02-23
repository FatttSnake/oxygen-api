package top.fatweb.oxygen.api.config

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.boot.jackson.JsonComponent
import org.springframework.context.annotation.Bean
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Date format configuration
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@JsonComponent
class DateFormatConfig {
    /**
     * The format of the time in response when request APIs
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @set:Value("\${spring.jackson.date-format}")
    lateinit var dateFormat: String

    /**
     * The timezone of the time in response when request APIs
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see TimeZone
     */
    @set:Value("\${spring.jackson.time-zone}}")
    lateinit var timeZone: TimeZone

    @Bean
    fun jackson2ObjectMapperBuilder() = Jackson2ObjectMapperBuilderCustomizer {
        val tz = timeZone
        val df: DateFormat = SimpleDateFormat(dateFormat)
        df.timeZone = tz
        it.failOnEmptyBeans(false).failOnUnknownProperties(false)
            .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).dateFormat(df)
    }

    @Bean
    fun jackson2ObjectMapperBuilderCustomizer() =
        Jackson2ObjectMapperBuilderCustomizer {
            it.serializerByType(
                LocalDateTime::class.java, LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateFormat))
            )
        }

}
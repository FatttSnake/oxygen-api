package top.fatweb.api.annotation

import org.springframework.core.annotation.AliasFor

/**
 * Api controller version annotation
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ApiVersion(
    @get:AliasFor("version") val value: Int = 1,

    @get:AliasFor("value") val version: Int = 1
)

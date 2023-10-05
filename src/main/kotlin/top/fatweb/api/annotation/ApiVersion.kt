package top.fatweb.api.annotation

import org.springframework.core.annotation.AliasFor

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ApiVersion(
    @get:AliasFor("version") val value: Int = 1,

    @get:AliasFor("value") val version: Int = 1
)

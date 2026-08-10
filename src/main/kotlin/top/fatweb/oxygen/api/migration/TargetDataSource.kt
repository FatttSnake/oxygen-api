package top.fatweb.oxygen.api.migration

/**
 * Target migrate data source
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.3.0
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class TargetDataSource(
    val value: String
)

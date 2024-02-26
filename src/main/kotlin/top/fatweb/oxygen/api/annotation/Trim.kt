package top.fatweb.oxygen.api.annotation

import java.lang.annotation.Inherited


/**
 * Trim string annotation
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS, AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
@Inherited
annotation class Trim
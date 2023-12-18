package top.fatweb.api.annotation

import top.fatweb.api.entity.system.EventLog

/**
 * Event log record annotation
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class EventLogRecord(
    val event: EventLog.Event
)
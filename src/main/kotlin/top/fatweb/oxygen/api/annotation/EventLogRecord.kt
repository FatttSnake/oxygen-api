package top.fatweb.oxygen.api.annotation

import top.fatweb.oxygen.api.entity.system.EventLog

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
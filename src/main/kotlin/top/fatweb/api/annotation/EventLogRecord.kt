package top.fatweb.api.annotation

import top.fatweb.api.entity.system.EventLog

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class EventLogRecord(
    val event: EventLog.Event
)
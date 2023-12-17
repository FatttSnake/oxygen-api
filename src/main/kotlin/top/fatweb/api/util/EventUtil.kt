package top.fatweb.api.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import top.fatweb.api.entity.system.EventLog
import top.fatweb.api.service.system.IEventLogService

@Component
class EventUtil(
    private val eventLogService: IEventLogService
) {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    fun record(event: EventLog.Event) {
        try {
            eventLogService.save(EventLog().apply {
                this.event = event
                operateUserId = WebUtil.getLoginUserId() ?: -1
            })
        } catch (e: Exception) {
            logger.error("Cannot record event!!!", e)
        }
    }
}
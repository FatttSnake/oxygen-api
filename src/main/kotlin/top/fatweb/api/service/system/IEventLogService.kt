package top.fatweb.api.service.system

import com.baomidou.mybatisplus.extension.service.IService
import top.fatweb.api.annotation.EventLogRecord
import top.fatweb.api.entity.system.EventLog

/**
 * Event log service interface
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
interface IEventLogService : IService<EventLog> {
    fun saveEvent(annotation: EventLogRecord, userId: Long)
}
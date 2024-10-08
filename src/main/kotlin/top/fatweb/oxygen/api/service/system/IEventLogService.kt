package top.fatweb.oxygen.api.service.system

import com.baomidou.mybatisplus.extension.service.IService
import top.fatweb.oxygen.api.annotation.EventLogRecord
import top.fatweb.oxygen.api.entity.system.EventLog

/**
 * Event log service interface
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
interface IEventLogService : IService<EventLog> {
    /**
     * Save event
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see EventLogRecord
     */
    fun saveEvent(annotation: EventLogRecord, userId: Long)
}
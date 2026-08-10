package top.fatweb.oxygen.api.service.system

import com.baomidou.mybatisplus.extension.service.IService
import top.fatweb.oxygen.api.annotation.EventLogRecord
import top.fatweb.oxygen.api.entity.system.EventLog

/**
 * Event log service interface
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see IService
 * @see EventLog
 */
interface IEventLogService : IService<EventLog> {
    /**
     * Save event
     *
     * @param annotation Annotation
     * @param userId User ID
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see EventLogRecord
     */
    fun saveEvent(annotation: EventLogRecord, userId: Long)
}

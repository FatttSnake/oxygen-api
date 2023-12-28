package top.fatweb.oxygen.api.service.system.impl

import com.baomidou.dynamic.datasource.annotation.DS
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import top.fatweb.oxygen.api.annotation.EventLogRecord
import top.fatweb.oxygen.api.entity.system.EventLog
import top.fatweb.oxygen.api.mapper.system.EventLogMapper
import top.fatweb.oxygen.api.service.system.IEventLogService

@DS("sqlite")
@Service
class EventLogServiceImpl : ServiceImpl<EventLogMapper, EventLog>(), IEventLogService {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    override fun saveEvent(annotation: EventLogRecord, userId: Long) {
        try {
            this.save(EventLog().apply {
                this.event = annotation.event
                operateUserId = userId
            })
        } catch (e: Exception) {
            logger.error("Cannot record event!!!", e)
        }
    }
}
package top.fatweb.api.service.system.impl

import com.baomidou.dynamic.datasource.annotation.DS
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.api.entity.system.EventLog
import top.fatweb.api.mapper.system.EventLogMapper
import top.fatweb.api.service.system.IEventLogService

@DS("sqlite")
@Service
class EventLogServiceImpl : ServiceImpl<EventLogMapper, EventLog>(), IEventLogService
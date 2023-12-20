package top.fatweb.api.service.system.impl

import com.baomidou.dynamic.datasource.annotation.DS
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.api.entity.system.StatisticsLog
import top.fatweb.api.mapper.system.StatisticsLogMapper
import top.fatweb.api.service.system.IStatisticsLogService

@DS("sqlite")
@Service
class StatisticsLogServiceImpl : ServiceImpl<StatisticsLogMapper, StatisticsLog>(), IStatisticsLogService
package top.fatweb.oxygen.api.service.system.impl

import com.baomidou.dynamic.datasource.annotation.DS
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.oxygen.api.entity.system.StatisticsLog
import top.fatweb.oxygen.api.mapper.system.StatisticsLogMapper
import top.fatweb.oxygen.api.service.system.IStatisticsLogService

@DS("sqlite")
@Service
class StatisticsLogServiceImpl : ServiceImpl<StatisticsLogMapper, StatisticsLog>(), IStatisticsLogService
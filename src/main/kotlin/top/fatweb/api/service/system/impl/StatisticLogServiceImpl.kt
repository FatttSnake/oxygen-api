package top.fatweb.api.service.system.impl

import com.baomidou.dynamic.datasource.annotation.DS
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.api.entity.system.StatisticLog
import top.fatweb.api.mapper.system.StatisticLogMapper
import top.fatweb.api.service.system.IStatisticLogService

@DS("sqlite")
@Service
class StatisticLogServiceImpl : ServiceImpl<StatisticLogMapper, StatisticLog>(), IStatisticLogService
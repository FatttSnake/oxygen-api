package top.fatweb.api.mapper.system

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import top.fatweb.api.entity.system.StatisticsLog

@Mapper
interface StatisticsLogMapper : BaseMapper<StatisticsLog>
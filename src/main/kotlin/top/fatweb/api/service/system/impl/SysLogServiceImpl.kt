package top.fatweb.api.service.system.impl

import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.core.metadata.OrderItem
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.api.entity.system.SysLog
import top.fatweb.api.mapper.system.SysLogMapper
import top.fatweb.api.param.system.SysLogGetParam
import top.fatweb.api.service.system.ISysLogService
import top.fatweb.api.util.PageUtil

/**
 * System log service implement
 *
 * @author FatttSnake
 * @since 1.0.0
 */
@Service
class SysLogServiceImpl : ServiceImpl<SysLogMapper, SysLog>(), ISysLogService {
    override fun getPage(sysLogGetParam: SysLogGetParam?): IPage<SysLog> {
        val sysLogPage = Page<SysLog>(sysLogGetParam?.currentPage ?: 1, sysLogGetParam?.pageSize ?: 20)

        PageUtil.setPageSort(sysLogGetParam, sysLogPage, OrderItem.desc("start_time"))

        return baseMapper.selectPage(
            sysLogPage,
            sysLogGetParam?.logType?.split(","),
            sysLogGetParam?.requestMethod?.split(","),
            sysLogGetParam?.searchRequestUrl,
            sysLogGetParam?.searchRegex ?: false,
            sysLogGetParam?.searchStartTime,
            sysLogGetParam?.searchEndTime
        )
    }
}

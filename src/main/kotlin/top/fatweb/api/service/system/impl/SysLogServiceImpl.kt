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
import top.fatweb.api.util.StrUtil

/**
 * <p>
 * 系统日志表 服务实现类
 * </p>
 *
 * @author FatttSnake
 * @since 2023-10-18
 */
@Service
class SysLogServiceImpl : ServiceImpl<SysLogMapper, SysLog>(), ISysLogService {
    override fun getPage(sysLogGetParam: SysLogGetParam?): IPage<SysLog> {
        val sysLogPage = Page<SysLog>(sysLogGetParam?.currentPage ?: 1, sysLogGetParam?.pageSize ?: 20)

        if (sysLogGetParam?.sortField == null && sysLogGetParam?.sortOrder == null) {
            sysLogPage.addOrder(OrderItem.desc("start_time"))
        } else {
            sysLogPage.addOrder(
                if (sysLogGetParam.sortOrder?.startsWith(
                        "desc", true
                    ) == true
                ) OrderItem.desc(StrUtil.upperToUnderLetter(sysLogGetParam.sortField)) else OrderItem.asc(
                    StrUtil.upperToUnderLetter(
                        sysLogGetParam.sortField
                    )
                )
            )
        }

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

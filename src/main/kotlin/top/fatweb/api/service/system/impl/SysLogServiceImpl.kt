package top.fatweb.api.service.system.impl

import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.core.metadata.OrderItem
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.api.entity.system.SysLog
import top.fatweb.api.mapper.system.SysLogMapper
import top.fatweb.api.service.system.ISysLogService

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
    override fun getPage(page: Long, pageSize: Long): IPage<SysLog> {
        val sysLogPage = Page<SysLog>(page, pageSize)
        sysLogPage.addOrder(OrderItem.desc("start_time"))

        return baseMapper.selectPage(sysLogPage)
    }
}

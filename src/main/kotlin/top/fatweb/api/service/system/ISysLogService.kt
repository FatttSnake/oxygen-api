package top.fatweb.api.service.system;

import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.service.IService
import top.fatweb.api.entity.system.SysLog

/**
 * <p>
 * 系统日志表 服务类
 * </p>
 *
 * @author FatttSnake
 * @since 2023-10-18
 */
interface ISysLogService : IService<SysLog> {
    fun getPage(page: Long, pageSize: Long): IPage<SysLog>
}

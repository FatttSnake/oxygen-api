package top.fatweb.api.service.system

import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.service.IService
import top.fatweb.api.entity.system.SysLog
import top.fatweb.api.param.system.SysLogGetParam

/**
 * <p>
 * 系统日志表 服务类
 * </p>
 *
 * @author FatttSnake
 * @since 2023-10-18
 */
interface ISysLogService : IService<SysLog> {
    fun getPage(sysLogGetParam: SysLogGetParam?): IPage<SysLog>
}

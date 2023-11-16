package top.fatweb.api.service.system

import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.service.IService
import top.fatweb.api.entity.system.SysLog
import top.fatweb.api.param.system.SysLogGetParam

/**
 * System log service interface
 *
 * @author FatttSnake
 * @since 1.0.0
 */
interface ISysLogService : IService<SysLog> {
    fun getPage(sysLogGetParam: SysLogGetParam?): IPage<SysLog>
}

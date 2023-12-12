package top.fatweb.api.service.system

import com.baomidou.mybatisplus.extension.service.IService
import top.fatweb.api.entity.system.SysLog
import top.fatweb.api.param.system.SysLogGetParam
import top.fatweb.api.vo.PageVo
import top.fatweb.api.vo.system.SysLogVo

/**
 * System log service interface
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see IService
 * @see SysLog
 */
interface ISysLogService : IService<SysLog> {
    /**
     * Get system log in page
     *
     * @param sysLogGetParam Get system log parameters
     * @return IPage<SysLog> object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see SysLogGetParam
     * @see PageVo
     * @see SysLogVo
     */
    fun getPage(sysLogGetParam: SysLogGetParam?): PageVo<SysLogVo>
}

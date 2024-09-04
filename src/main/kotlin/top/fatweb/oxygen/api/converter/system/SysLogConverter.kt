package top.fatweb.oxygen.api.converter.system

import com.baomidou.mybatisplus.core.metadata.IPage
import top.fatweb.oxygen.api.entity.system.SysLog
import top.fatweb.oxygen.api.vo.PageVo
import top.fatweb.oxygen.api.vo.system.SysLogVo

/**
 * System log converter
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
object SysLogConverter {
    /**
     * Convert IPage<SysLog> object into PageVo<SysLogVo> object
     *
     * @param syslogPage IPage<Syslog> object
     * @return PageVo<SysLogVo> object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see IPage
     * @see SysLog
     * @see PageVo
     * @see SysLogVo
     */
    fun sysLogPageToSysLogPageVo(syslogPage: IPage<SysLog>) = PageVo(
        syslogPage.total,
        syslogPage.pages,
        syslogPage.size,
        syslogPage.current,
        syslogPage.records.map { sysLog ->
            SysLogVo(
                id = sysLog.id,
                logType = sysLog.logType,
                operateUserId = sysLog.operateUserId,
                operateTime = sysLog.operateTime,
                requestUri = sysLog.requestUri,
                requestMethod = sysLog.requestMethod,
                requestParams = sysLog.requestParams,
                requestIp = sysLog.requestIp,
                requestServerAddress = sysLog.requestServerAddress,
                exception = sysLog.exception?.let { it == 1},
                exceptionInfo = sysLog.exceptionInfo,
                startTime = sysLog.startTime,
                endTime = sysLog.endTime,
                executeTime = sysLog.executeTime,
                userAgent = sysLog.userAgent,
                operateUsername = sysLog.operateUsername
            )
        })

}
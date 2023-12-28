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
        syslogPage.records.map {
            SysLogVo(
                id = it.id,
                logType = it.logType,
                operateUserId = it.operateUserId,
                operateTime = it.operateTime,
                requestUri = it.requestUri,
                requestMethod = it.requestMethod,
                requestParams = it.requestParams,
                requestIp = it.requestIp,
                requestServerAddress = it.requestServerAddress,
                exception = it.exception == 1,
                exceptionInfo = it.exceptionInfo,
                startTime = it.startTime,
                endTime = it.endTime,
                executeTime = it.executeTime,
                userAgent = it.userAgent,
                operateUsername = it.operateUsername
            )
        })

}
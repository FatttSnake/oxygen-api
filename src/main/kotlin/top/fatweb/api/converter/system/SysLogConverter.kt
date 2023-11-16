package top.fatweb.api.converter.system

import com.baomidou.mybatisplus.core.metadata.IPage
import top.fatweb.api.entity.system.SysLog
import top.fatweb.api.vo.PageVo
import top.fatweb.api.vo.system.SysLogVo

/**
 * System log converter
 *
 * @author FatttSnake
 * @since 1.0.0
 */
object SysLogConverter {
    fun sysLogPageToSysLogPageVo(syslogPage: IPage<SysLog>): PageVo<SysLogVo> = PageVo(
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
package top.fatweb.api.converter

import com.baomidou.mybatisplus.core.metadata.IPage
import top.fatweb.api.entity.system.SysLog
import top.fatweb.api.vo.PageVo
import top.fatweb.api.vo.system.SysLogGetVo

object SysLogConverter {
    fun sysLogPageToSysLogPageVo(syslogPage: IPage<SysLog>): PageVo<SysLogGetVo> = PageVo(
        syslogPage.total,
        syslogPage.pages,
        syslogPage.size,
        syslogPage.current,
        syslogPage.records.map {
            SysLogGetVo(
                it.id,
                it.logType,
                it.operateUserId,
                it.operateTime,
                it.requestUri,
                it.requestMethod,
                it.requestParams,
                it.requestIp,
                it.requestServerAddress,
                it.isException,
                it.exceptionInfo,
                it.startTime,
                it.endTime,
                it.executeTime,
                it.userAgent
            )
        })

}
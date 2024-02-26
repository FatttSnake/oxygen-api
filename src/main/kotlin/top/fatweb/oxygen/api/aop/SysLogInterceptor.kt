package top.fatweb.oxygen.api.aop

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.core.MethodParameter
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice
import top.fatweb.oxygen.api.entity.common.ResponseCode
import top.fatweb.oxygen.api.entity.common.ResponseResult
import top.fatweb.oxygen.api.entity.system.SysLog
import top.fatweb.oxygen.api.service.system.ISysLogService
import top.fatweb.oxygen.api.util.WebUtil
import top.fatweb.oxygen.api.vo.permission.LoginVo
import java.net.URI
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.temporal.ChronoUnit
import java.util.*
import java.util.concurrent.Executor

/**
 * System log interceptor
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see Executor
 * @see ISysLogService
 * @see HandlerInterceptor
 * @see ResponseBodyAdvice
 */
@ControllerAdvice
class SysLogInterceptor(
    @Qualifier("applicationTaskExecutor") private val customThreadPoolTaskExecutor: Executor,
    private val sysLogService: ISysLogService
) : HandlerInterceptor, ResponseBodyAdvice<Any> {
    private val sysLogThreadLocal = ThreadLocal<SysLog>()
    private val resultThreadLocal = ThreadLocal<Any>()

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val sysLog = SysLog().apply {
            operateUserId = WebUtil.getLoginUserId() ?: -1
            startTime = LocalDateTime.now(ZoneOffset.UTC)
            requestUri = URI(request.requestURI).path
            requestParams = formatParams(request.parameterMap)
            requestMethod = request.method
            requestIp = request.remoteAddr
            requestServerAddress = "${request.scheme}://${request.serverName}:${request.serverPort}"
            userAgent = request.getHeader("User-Agent")
        }

        sysLogThreadLocal.set(sysLog)

        return true
    }

    override fun afterCompletion(
        request: HttpServletRequest, response: HttpServletResponse, handler: Any, ex: Exception?
    ) {
        val sysLog = sysLogThreadLocal.get()
        val result = resultThreadLocal.get()
        sysLog.endTime = LocalDateTime.now(ZoneOffset.UTC)
        sysLog.executeTime = ChronoUnit.MILLIS.between(sysLog.startTime, sysLog.endTime)
        if (result is ResponseResult<*>) {
            if (result.success) {
                sysLog.apply {
                    logType = requestUri?.let {
                        when {
                            it.startsWith("/login") -> SysLog.LogType.LOGIN
                            it.startsWith("/logout") -> SysLog.LogType.LOGOUT
                            it.startsWith("/register") -> SysLog.LogType.REGISTER
                            it.startsWith("/system/statistics/") -> SysLog.LogType.STATISTICS
                            it.startsWith("/api/") -> SysLog.LogType.API
                            else -> SysLog.LogType.INFO
                        }
                    } ?: SysLog.LogType.INFO
                    exception = 0
                }
                if (result.data is LoginVo) {
                    sysLog.operateUserId = result.data.userId ?: -1
                }
            } else {
                sysLog.apply {
                    logType = SysLog.LogType.ERROR
                    exception = 1
                    exceptionInfo = result.msg
                }
            }

            customThreadPoolTaskExecutor.execute(SaveLogThread(sysLog, sysLogService))
        }
        sysLogThreadLocal.remove()
    }

    private fun formatParams(parameterMap: Map<String, Array<String>>): String {
        val params = StringJoiner("&")

        parameterMap.forEach {
            params.add("${it.key}=${if (it.key.endsWith("password", true)) "*" else it.value.joinToString(",")}")
        }

        return params.toString()
    }

    private class SaveLogThread(val sysLog: SysLog, val sysLogService: ISysLogService) : Thread() {
        override fun run() {
            sysLog.operateTime = LocalDateTime.now(ZoneOffset.UTC)
            sysLogService.save(sysLog)
        }
    }

    override fun supports(returnType: MethodParameter, converterType: Class<out HttpMessageConverter<*>>): Boolean =
        true

    override fun beforeBodyWrite(
        body: Any?,
        returnType: MethodParameter,
        selectedContentType: MediaType,
        selectedConverterType: Class<out HttpMessageConverter<*>>,
        request: ServerHttpRequest,
        response: ServerHttpResponse
    ): Any? {
        resultThreadLocal.set(body)

        if (body is ResponseResult<*> && body.code == ResponseCode.SYSTEM_ERROR.code) {
            return ResponseResult.build(body.code, body.success, "fail", body.data)
        }

        return body
    }
}
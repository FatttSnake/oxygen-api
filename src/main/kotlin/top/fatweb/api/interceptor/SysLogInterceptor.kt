package top.fatweb.api.interceptor

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.MethodParameter
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice
import top.fatweb.api.entity.system.SysLog
import top.fatweb.api.entity.common.ResponseResult
import top.fatweb.api.service.system.ISysLogService
import top.fatweb.api.util.WebUtil
import java.net.URI
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.temporal.ChronoUnit
import java.util.*
import java.util.concurrent.Executor

@ControllerAdvice
class SysLogInterceptor(
    private val customThreadPoolTaskExecutor: Executor, private val sysLogService: ISysLogService
) : HandlerInterceptor, ResponseBodyAdvice<Any> {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)
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
                    logType = "INFO"
                    isException = 0
                }
            } else {
                sysLog.apply {
                    logType = "ERROR"
                    isException = 1
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
        return body
    }
}
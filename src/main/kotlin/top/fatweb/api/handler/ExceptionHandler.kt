package top.fatweb.api.handler

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.security.authentication.InsufficientAuthenticationException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import top.fatweb.api.entity.common.ResponseCode
import top.fatweb.api.entity.common.ResponseResult

@RestControllerAdvice
class ExceptionHandler {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    @ExceptionHandler(value = [Exception::class])
    fun exceptionHandler(e: Exception): ResponseResult<*> {
        return when (e) {
            is InsufficientAuthenticationException -> {
                log.debug(e.localizedMessage, e)
                ResponseResult.fail(ResponseCode.SYSTEM_UNAUTHORIZED, e.localizedMessage, null)
            }

            is HttpMessageNotReadableException -> {
                log.debug(e.localizedMessage, e)
                ResponseResult.fail(ResponseCode.SYSTEM_REQUEST_ILLEGAL, e.localizedMessage.split(":")[0], null)
            }

            else -> {
                log.error(e.localizedMessage, e)
                ResponseResult.fail(ResponseCode.SYSTEM_ERROR, data = null)
            }
        }
    }
}
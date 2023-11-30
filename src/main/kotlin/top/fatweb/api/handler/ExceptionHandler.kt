package top.fatweb.api.handler

import com.auth0.jwt.exceptions.JWTDecodeException
import com.auth0.jwt.exceptions.SignatureVerificationException
import com.auth0.jwt.exceptions.TokenExpiredException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.dao.DuplicateKeyException
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.jdbc.BadSqlGrammarException
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.authentication.*
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import top.fatweb.api.entity.common.ResponseCode
import top.fatweb.api.entity.common.ResponseResult
import top.fatweb.api.exception.NoRecordFoundException
import top.fatweb.api.exception.TokenHasExpiredException
import top.fatweb.avatargenerator.AvatarException

/**
 * Exception handler
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@RestControllerAdvice
class ExceptionHandler {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @ExceptionHandler(value = [Exception::class])
    fun exceptionHandler(e: Exception): ResponseResult<*> {
        return when (e) {
            is HttpRequestMethodNotSupportedException -> {
                logger.debug(e.localizedMessage, e)
                ResponseResult.fail(ResponseCode.SYSTEM_REQUEST_ILLEGAL, e.localizedMessage, null)
            }

            is HttpMessageNotReadableException -> {
                logger.debug(e.localizedMessage, e)
                ResponseResult.fail(ResponseCode.SYSTEM_REQUEST_ILLEGAL, e.localizedMessage.split(":")[0], null)
            }

            is MethodArgumentNotValidException -> {
                logger.debug(e.localizedMessage, e)
                val errorMessage = e.allErrors.map { error -> error.defaultMessage }.joinToString(". ")
                ResponseResult.fail(ResponseCode.SYSTEM_ARGUMENT_NOT_VALID, errorMessage, null)
            }

            is InsufficientAuthenticationException -> {
                logger.debug(e.localizedMessage, e)
                ResponseResult.fail(ResponseCode.PERMISSION_UNAUTHORIZED, e.localizedMessage, null)
            }

            is LockedException -> {
                logger.debug(e.localizedMessage, e)
                ResponseResult.fail(ResponseCode.PERMISSION_USER_LOCKED, "User account has been locked", null)
            }

            is AccountExpiredException -> {
                logger.debug(e.localizedMessage, e)
                ResponseResult.fail(ResponseCode.PERMISSION_USER_EXPIRED, "User account has expired", null)
            }

            is CredentialsExpiredException -> {
                logger.debug(e.localizedMessage, e)
                ResponseResult.fail(
                    ResponseCode.PERMISSION_USER_CREDENTIALS_EXPIRED,
                    "User credentials have expired",
                    null
                )
            }

            is DisabledException -> {
                logger.debug(e.localizedMessage, e)
                ResponseResult.fail(ResponseCode.PERMISSION_USER_CREDENTIALS_EXPIRED, "User has been disabled", null)
            }

            is TokenExpiredException -> {
                logger.debug(e.localizedMessage, e)
                ResponseResult.fail(ResponseCode.PERMISSION_TOKEN_HAS_EXPIRED, e.localizedMessage, null)
            }

            is InternalAuthenticationServiceException -> {
                logger.debug(e.localizedMessage, e)
                ResponseResult.fail(ResponseCode.PERMISSION_USERNAME_NOT_FOUND, "Username not found", null)
            }

            is BadCredentialsException -> {
                logger.debug(e.localizedMessage, e)
                ResponseResult.fail(
                    ResponseCode.PERMISSION_LOGIN_USERNAME_PASSWORD_ERROR,
                    "Wrong user name or password",
                    null
                )
            }

            is SignatureVerificationException, is JWTDecodeException -> {
                logger.debug(e.localizedMessage, e)
                ResponseResult.fail(ResponseCode.PERMISSION_TOKEN_ILLEGAL, "Token illegal", null)
            }

            is TokenHasExpiredException -> {
                logger.debug(e.localizedMessage, e)
                ResponseResult.fail(ResponseCode.PERMISSION_TOKEN_HAS_EXPIRED, e.localizedMessage, null)
            }

            is AccessDeniedException -> {
                logger.debug(e.localizedMessage, e)
                ResponseResult.fail(ResponseCode.PERMISSION_ACCESS_DENIED, "Access Denied", null)
            }

            is BadSqlGrammarException -> {
                logger.debug(e.localizedMessage, e)
                ResponseResult.fail(ResponseCode.DATABASE_EXECUTE_ERROR, "Incorrect SQL syntax", null)
            }

            is DuplicateKeyException -> {
                logger.debug(e.localizedMessage, e)
                ResponseResult.fail(ResponseCode.DATABASE_DUPLICATE_KEY, "Duplicate key", null)
            }

            is NoRecordFoundException -> {
                logger.debug(e.localizedMessage, e)
                ResponseResult.fail(ResponseCode.DATABASE_NO_RECORD_FOUND, e.localizedMessage, null)
            }

            is AvatarException -> {
                logger.debug(e.localizedMessage, e)
                ResponseResult.fail(ResponseCode.API_AVATAR_ERROR, e.localizedMessage, null)
            }

            else -> {
                logger.error(e.localizedMessage, e)
                ResponseResult.fail(ResponseCode.SYSTEM_ERROR, e.toString(), null)
            }
        }
    }
}
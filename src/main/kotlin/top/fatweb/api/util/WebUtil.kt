package top.fatweb.api.util

import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.context.SecurityContextHolder
import top.fatweb.api.constant.SecurityConstants
import top.fatweb.api.entity.permission.LoginUser

object WebUtil {
    fun getLoginUser() = if (SecurityContextHolder.getContext().authentication.principal is String) null
    else SecurityContextHolder.getContext().authentication.principal as LoginUser

    fun getLoginUserId() = getLoginUser()?.user?.id

    fun getToken(tokenWithPrefix: String) = tokenWithPrefix.removePrefix(SecurityConstants.tokenPrefix)

    fun getToken(request: HttpServletRequest) = getToken(request.getHeader(SecurityConstants.headerString))
}
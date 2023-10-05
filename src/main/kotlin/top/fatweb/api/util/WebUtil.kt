package top.fatweb.api.util

import org.springframework.security.core.context.SecurityContextHolder
import top.fatweb.api.entity.permission.LoginUser

object WebUtil {
    fun getLoginUser() = SecurityContextHolder.getContext().authentication.principal as LoginUser

    fun getLoginUserId() = getLoginUser().user.id
}
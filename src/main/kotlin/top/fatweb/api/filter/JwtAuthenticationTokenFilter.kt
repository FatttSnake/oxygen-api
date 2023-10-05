package top.fatweb.api.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import top.fatweb.api.constant.SecurityConstants
import top.fatweb.api.entity.permission.LoginUser
import top.fatweb.api.exception.TokenHasExpiredException
import top.fatweb.api.util.JwtUtil
import top.fatweb.api.util.RedisUtil
import java.util.concurrent.TimeUnit

@Component
class JwtAuthenticationTokenFilter(private val redisUtil: RedisUtil) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val tokenWithPrefix = request.getHeader(SecurityConstants.headerString)

        if (!StringUtils.hasText(tokenWithPrefix) || "/error/thrown" == request.servletPath) {
            filterChain.doFilter(request, response)
            return
        }

        val token = tokenWithPrefix.removePrefix(SecurityConstants.tokenPrefix)
        JwtUtil.parseJwt(token)

        val redisKey = "${SecurityConstants.jwtIssuer}_login:" + token.substring(0, 32)
        val loginUser = redisUtil.getObject<LoginUser>(redisKey)
        loginUser ?: let { throw TokenHasExpiredException() }

        redisUtil.setExpire(redisKey, 20, TimeUnit.MINUTES)

        val authenticationToken = UsernamePasswordAuthenticationToken(loginUser, null, loginUser.authorities)
        SecurityContextHolder.getContext().authentication = authenticationToken

        filterChain.doFilter(request, response)
    }
}
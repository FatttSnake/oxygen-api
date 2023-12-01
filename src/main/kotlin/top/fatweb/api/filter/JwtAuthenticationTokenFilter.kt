package top.fatweb.api.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import top.fatweb.api.entity.permission.LoginUser
import top.fatweb.api.exception.TokenHasExpiredException
import top.fatweb.api.properties.SecurityProperties
import top.fatweb.api.util.JwtUtil
import top.fatweb.api.util.RedisUtil
import top.fatweb.api.util.WebUtil

/**
 * Jwt authentication token filter
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Component
class JwtAuthenticationTokenFilter(private val redisUtil: RedisUtil) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain
    ) {
        val tokenWithPrefix = request.getHeader(SecurityProperties.headerString)

        if (!StringUtils.hasText(tokenWithPrefix) || "/error/thrown" == request.servletPath) {
            filterChain.doFilter(request, response)
            return
        }

        val token = WebUtil.getToken(tokenWithPrefix)
        JwtUtil.parseJwt(token)

        val redisKeyPattern = "${SecurityProperties.jwtIssuer}_login_*:" + token
        val redisKeys = redisUtil.keys(redisKeyPattern)
        if (redisKeys.isEmpty()) {
            throw TokenHasExpiredException()
        }

        val loginUser = redisUtil.getObject<LoginUser>(redisKeys.first())
        loginUser ?: let { throw TokenHasExpiredException() }

        redisUtil.setExpire(redisKeys.first(), SecurityProperties.redisTtl, SecurityProperties.redisTtlUnit)

        val authenticationToken = UsernamePasswordAuthenticationToken(loginUser, null, loginUser.authorities)
        SecurityContextHolder.getContext().authentication = authenticationToken

        filterChain.doFilter(request, response)
    }
}
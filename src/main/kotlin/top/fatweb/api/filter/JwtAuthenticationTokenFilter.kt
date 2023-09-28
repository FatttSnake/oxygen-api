package top.fatweb.api.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import top.fatweb.api.constants.SecurityConstants
import top.fatweb.api.utils.RedisUtil

class JwtAuthenticationTokenFilter(private val redisUtil: RedisUtil) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = request.getHeader(SecurityConstants.headerString)

        if (!StringUtils.hasText(token) || "/error/thrown" == request.servletPath) {
            filterChain.doFilter(request, response)
            return
        }


    }
}
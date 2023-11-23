package top.fatweb.api.filter

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import org.springframework.stereotype.Component

/**
 * Exception filter
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Component
class ExceptionFilter : Filter {
    override fun doFilter(
        servletRequest: ServletRequest?, servletResponse: ServletResponse?, filterChain: FilterChain?
    ) {
        try {
            filterChain!!.doFilter(servletRequest, servletResponse)
        } catch (e: Exception) {
            servletRequest?.setAttribute("filter.error", e)
            servletRequest?.getRequestDispatcher("/error/thrown")?.forward(servletRequest, servletResponse)
        }
    }
}
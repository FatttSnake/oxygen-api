package top.fatweb.api.filter

import jakarta.servlet.*
import java.lang.Exception

class ExceptionFilter : Filter {
    override fun doFilter(servletRequest: ServletRequest?, servletResponse: ServletResponse?, filterChain: FilterChain?) {
        try {
            filterChain!!.doFilter(servletRequest, servletResponse)
        } catch (e: Exception) {
            servletRequest?.setAttribute("filter.error", e)
            servletRequest?.getRequestDispatcher("/error/thrown")?.forward(servletRequest, servletResponse)
        }
    }
}
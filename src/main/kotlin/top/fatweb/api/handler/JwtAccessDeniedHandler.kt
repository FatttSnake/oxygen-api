package top.fatweb.api.handler

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component

/**
 * Jwt access denied handler
 *
 * @author FatttSnake
 * @since 1.0.0
 */
@Component
class JwtAccessDeniedHandler : AccessDeniedHandler {
    override fun handle(
        request: HttpServletRequest?, response: HttpServletResponse?, accessDeniedException: AccessDeniedException?
    ) {
        request?.setAttribute("filter.error", accessDeniedException)
        request?.getRequestDispatcher("/error/thrown")?.forward(request, response)
    }
}
package top.fatweb.oxygen.api.util

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.web.csrf.CookieCsrfTokenRepository
import org.springframework.security.web.csrf.CsrfToken
import org.springframework.security.web.csrf.CsrfTokenRepository

class CookieWithHeaderCsrfTokenRepository : CsrfTokenRepository {
    private val delegate = CookieCsrfTokenRepository.withHttpOnlyFalse().apply {
        cookiePath = "/token"
    }

    override fun generateToken(request: HttpServletRequest): CsrfToken {
        return delegate.generateToken(request)
    }

    override fun saveToken(token: CsrfToken?, request: HttpServletRequest, response: HttpServletResponse) {
        delegate.saveToken(token, request, response)
        token?.let {
            response.setHeader("X-XSRF-TOKEN", it.token)
        }
    }

    override fun loadToken(request: HttpServletRequest): CsrfToken? {
        return delegate.loadToken(request)
    }
}

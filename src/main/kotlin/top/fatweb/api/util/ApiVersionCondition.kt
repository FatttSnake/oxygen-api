package top.fatweb.api.util

import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.servlet.mvc.condition.RequestCondition

/**
 * Api version condition
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
class ApiVersionCondition(private val apiVersion: Int) : RequestCondition<ApiVersionCondition> {
    private val versionPrefixRegex = Regex(".*v(\\d+).*")

    override fun combine(other: ApiVersionCondition): ApiVersionCondition = ApiVersionCondition(other.apiVersion)

    override fun getMatchingCondition(request: HttpServletRequest): ApiVersionCondition? {
        versionPrefixRegex.matchAt(request.requestURI, 0)?.let {
            if (it.groupValues[1].toInt() >= this.apiVersion) {
                return this
            }
        }

        return null
    }

    override fun compareTo(other: ApiVersionCondition, request: HttpServletRequest): Int =
        other.apiVersion - this.apiVersion
}
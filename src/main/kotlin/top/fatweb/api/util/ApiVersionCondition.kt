package top.fatweb.api.util

import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.servlet.mvc.condition.RequestCondition
import java.util.regex.Pattern

/**
 * Api version condition
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
class ApiVersionCondition(private val apiVersion: Int) : RequestCondition<ApiVersionCondition> {
    private val versionPrefixPattern: Pattern = Pattern.compile(".*v(\\d+).*")

    override fun combine(other: ApiVersionCondition): ApiVersionCondition = ApiVersionCondition(other.apiVersion)

    override fun getMatchingCondition(request: HttpServletRequest): ApiVersionCondition? {
        val matcher = versionPrefixPattern.matcher(request.requestURI)
        if (matcher.find()) {
            val version = matcher.group(1).toInt()
            if (version >= this.apiVersion) {
                return this
            }
        }

        return null
    }

    override fun compareTo(other: ApiVersionCondition, request: HttpServletRequest): Int =
        other.apiVersion - this.apiVersion
}
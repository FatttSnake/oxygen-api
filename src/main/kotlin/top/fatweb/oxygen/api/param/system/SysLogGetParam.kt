package top.fatweb.oxygen.api.param.system

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.format.annotation.DateTimeFormat
import top.fatweb.oxygen.api.param.PageSortParam
import java.time.LocalDateTime

/**
 * Get system log parameters
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see PageSortParam
 */
@Schema(description = "获取系统日志请求参数")
data class SysLogGetParam(
    /**
     * Log type
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(
        description = "类型过滤(多个使用逗号分隔)",
        allowableValues = ["INFO", "LOGIN", "LOGOUT", "REGISTER", "STATISTICS", "API", "ERROR"],
        example = "INFO"
    )
    val logType: String?,

    /**
     * Request method to filter
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(
        description = "请求方式过滤(多个使用逗号分隔)",
        allowableValues = ["GET", "POST", "PUT", "PATCH", "DELETE", "DELETE", "OPTIONS"],
        example = "GET,POST"
    )
    val requestMethod: String?,

    /**
     * Request URL to search for
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "查询请求 Url")
    val searchRequestUrl: String?,

    /**
     * Use regex
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    /*
        @Schema(description = "查询使用正则表达式", allowableValues = ["true", "false"], defaultValue = "false")
        val searchRegex: Boolean = false,
    */

    /**
     * Start time to search for
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see LocalDateTime
     */
    @Schema(description = "查询开始时间", example = "1900-01-01T00:00:00.000Z")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val searchStartTime: LocalDateTime?,

    /**
     * End time to search for
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see LocalDateTime
     */
    @Schema(description = "查询结束时间", example = "1900-01-01T00:00:00.000Z")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val searchEndTime: LocalDateTime?
) : PageSortParam()
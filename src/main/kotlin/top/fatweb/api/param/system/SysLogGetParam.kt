package top.fatweb.api.param.system

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.format.annotation.DateTimeFormat
import top.fatweb.api.param.PageSortParam
import java.time.LocalDateTime

/**
 * System log get param
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Schema(description = "获取系统日志请求参数")
data class SysLogGetParam(
    @Schema(description = "类型过滤(多个使用逗号分隔)", example = "INFO", allowableValues = ["INFO", "ERROR"])
    val logType: String? = null,

    @Schema(
        description = "请求方式过滤(多个使用逗号分隔)",
        example = "GET,POST",
        allowableValues = ["GET", "POST", "PUT", "PATCH", "DELETE", "DELETE", "OPTIONS"]
    )
    val requestMethod: String? = null,

    @Schema(description = "查询请求 Url")
    val searchRequestUrl: String? = null,

    @Schema(description = "查询使用正则表达式")
    val searchRegex: Boolean = false,

    @Schema(description = "查询开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val searchStartTime: LocalDateTime? = null,

    @Schema(description = "查询结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val searchEndTime: LocalDateTime? = null
) : PageSortParam()
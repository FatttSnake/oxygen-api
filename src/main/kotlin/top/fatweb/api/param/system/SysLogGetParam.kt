package top.fatweb.api.param.system

import io.swagger.v3.oas.annotations.media.Schema
import top.fatweb.api.param.PageParam

@Schema(description = "获取系统日志请求参数")
data class SysLogGetParam(
    @Schema(description = "排序字段", example = "id")
    val sortField: String? = null,

    @Schema(description = "排序方式", example = "desc", allowableValues = ["desc", "asc"])
    val sortOrder: String? = null,

    @Schema(description = "类型过滤(多个使用逗号分隔)", example = "INFO", allowableValues = ["INFO", "ERROR"])
    val logType: String? = null,

    @Schema(
        description = "请求方式过滤(多个使用逗号分隔)",
        example = "GET,POST",
        allowableValues = ["GET", "POST", "PUT", "PATCH", "DELETE", "DELETE", "OPTIONS"]
    )
    val requestMethod: String? = null
) : PageParam()
package top.fatweb.oxygen.api.param.tool

import io.swagger.v3.oas.annotations.media.Schema
import top.fatweb.oxygen.api.param.PageSortParam

/**
 * Get tool template parameters
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see PageSortParam
 */
@Schema(description = "获取工具模板请求参数")
data class ToolTemplateGetParam(
    /**
     * Platform
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(
        description = "平台过滤(多个使用逗号分隔)",
        allowableValues = ["WEB", "DESKTOP", "ANDROID"],
        example = "WEB"
    )
    val platform: String?
) : PageSortParam()

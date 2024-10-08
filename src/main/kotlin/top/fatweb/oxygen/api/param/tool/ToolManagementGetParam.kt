package top.fatweb.oxygen.api.param.tool

import io.swagger.v3.oas.annotations.media.Schema
import top.fatweb.oxygen.api.annotation.Trim
import top.fatweb.oxygen.api.param.PageSortParam

/**
 * Get tool parameters in tool management
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see PageSortParam
 */
@Trim
data class ToolManagementGetParam(
    /**
     * Type of search
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(
        description = "搜索类型",
        allowableValues = ["ALL", "NAME", "TOOL_ID", "NICKNAME", "USERNAME", "KEYWORD"],
        defaultValue = "ALL",
        example = "ALL"
    )
    val searchType: String = "ALL",

    /**
     * Value to search for
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Trim
    @Schema(description = "查询内容", example = "ToolName")
    var searchValue: String?,

    /**
     * Use regex
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(
        description = "查询使用正则表达式",
        allowableValues = ["true", "false"],
        defaultValue = "false",
        example = "false"
    )
    val searchRegex: Boolean = false,

    /**
     * Review
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(
        description = "审核状态过滤(多个使用逗号分隔)",
        allowableValues = ["NONE", "PROCESSING", "REJECT", "PASS"],
        example = "NONE,PASS"
    )
    val review: String?,

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

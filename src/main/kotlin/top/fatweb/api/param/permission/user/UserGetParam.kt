package top.fatweb.api.param.permission.user

import io.swagger.v3.oas.annotations.media.Schema
import top.fatweb.api.param.PageSortParam

/**
 * Get user parameters
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see PageSortParam
 */
@Schema(description = "用户查询请求参数")
data class UserGetParam(
    /**
     * Type of search
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "搜索类型", allowableValues = ["ALL", "ID", "USERNAME", "NICKNAME", "EMAIL"], defaultValue = "ALL")
    val searchType: String = "ALL",

    /**
     * Value to search for
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "查询内容")
    val searchValue: String?,

    /**
     * Use regex
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "查询使用正则表达式", allowableValues = ["true", "false"], defaultValue = "false")
    val searchRegex: Boolean = false,
) : PageSortParam()

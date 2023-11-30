package top.fatweb.api.param.permission.user

import io.swagger.v3.oas.annotations.media.Schema
import top.fatweb.api.param.PageSortParam

/**
 * User get param
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Schema(description = "用户查询请求参数")
data class UserGetParam(
    @Schema(description = "搜索类型", allowableValues = ["ALL", "ID", "USERNAME", "NICKNAME", "EMAIL"], defaultValue = "ALL")
    val searchType: String = "ALL",

    @Schema(description = "查询内容")
    val searchValue: String? = null,

    @Schema(description = "查询使用正则表达式", allowableValues = ["true", "false"], defaultValue = "false")
    val searchRegex: Boolean = false,
) : PageSortParam()

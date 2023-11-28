package top.fatweb.api.param.authentication

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
    @Schema(description = "查询内容")
    val searchValue: String? = null,

    @Schema(description = "查询使用正则表达式", allowableValues = ["true", "false"])
    val searchRegex: Boolean = false,
) : PageSortParam()

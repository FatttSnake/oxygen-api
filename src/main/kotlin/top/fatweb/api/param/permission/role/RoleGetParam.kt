package top.fatweb.api.param.permission.role

import io.swagger.v3.oas.annotations.media.Schema
import top.fatweb.api.param.PageSortParam

/**
 * Role get param
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Schema(description = "角色查询请求参数")
data class RoleGetParam(
    @Schema(description = "查询角色名称")
    val searchName: String? = null,

    @Schema(description = "查询使用正则表达式", allowableValues = ["true", "false"], defaultValue = "false")
    val searchRegex: Boolean = false,
) : PageSortParam()

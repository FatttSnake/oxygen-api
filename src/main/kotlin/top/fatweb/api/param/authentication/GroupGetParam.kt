package top.fatweb.api.param.authentication

import io.swagger.v3.oas.annotations.media.Schema
import top.fatweb.api.param.PageSortParam

data class GroupGetParam(
    @Schema(description = "查询用户组名称")
    val searchName: String? = null,

    @Schema(description = "查询使用正则表达式", allowableValues = ["true", "false"])
    val searchRegex: Boolean = false,
) : PageSortParam()

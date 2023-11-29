package top.fatweb.api.param.permission.group

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

/**
 * Group add param
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Schema(description = "用户组添加请求参数")
data class GroupAddParam(
    @Schema(description = "用户组名称")
    @field:NotBlank(message = "Name can not be blank")
    val name: String?,

    @Schema(description = "启用", allowableValues = ["true", "false"])
    val enable: Boolean? = true,

    @Schema(description = "角色 ID 列表")
    val roleIds: List<Long>? = null
)

package top.fatweb.api.param.permission.role

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

/**
 * Role update param
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Schema(description = "角色更新请求参数")
data class RoleUpdateParam(
    @Schema(description = "角色 ID")
    @field:NotNull(message = "Role id can not be null")
    val id: Long?,

    @Schema(description = "角色名称")
    @field:NotBlank(message = "Name can not be blank")
    val name: String?,

    @Schema(description = "启用", allowableValues = ["true", "false"], defaultValue = "true")
    val enable: Boolean = true,

    @Schema(description = "权限 ID 列表")
    val powerIds: List<Long>? = null
)

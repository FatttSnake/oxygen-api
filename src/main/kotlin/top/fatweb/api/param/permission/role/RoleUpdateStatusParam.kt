package top.fatweb.api.param.permission.role

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull

/**
 * Role change status param
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Schema(description = "角色更改状态请求参数")
data class RoleUpdateStatusParam(
    @Schema(description = "角色 ID")
    @field:NotNull(message = "Role id can not be null")
    val id: Long?,

    @Schema(description = "启用", allowableValues = ["true", "false"], defaultValue = "true")
    val enable: Boolean = true
)
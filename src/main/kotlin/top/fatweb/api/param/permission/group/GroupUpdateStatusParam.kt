package top.fatweb.api.param.permission.group

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull

/**
 * Group change status param
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Schema(description = "用户组更改状态请求参数")
data class GroupUpdateStatusParam(
    @Schema(description = "用户组 ID")
    @field:NotNull(message = "ID can not be null")
    val id: Long?,

    @Schema(description = "启用", allowableValues = ["true", "false"], defaultValue = "true")
    val enable: Boolean = true
)
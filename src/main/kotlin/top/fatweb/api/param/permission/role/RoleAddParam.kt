package top.fatweb.api.param.permission.role

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

/**
 * Add role parameters
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Schema(description = "角色添加请求参数")
data class RoleAddParam(
    /**
     * Name
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "角色名称")
    @field:NotBlank(message = "Name can not be blank")
    val name: String?,

    /**
     * Enable
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "启用", allowableValues = ["true", "false"], defaultValue = "true")
    val enable: Boolean = true,

    /**
     * List of power IDs
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "权限 ID 列表")
    val powerIds: List<Long>?
)

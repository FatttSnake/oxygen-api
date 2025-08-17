package top.fatweb.oxygen.api.param.tool

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

/**
 * Update tool base dist parameters
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.1.0
 */
@Schema(description = "更新工具基板产物请求参数")
data class ToolBaseUpdateDistParam(
    /**
     * ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     */
    @Schema(description = "ID", required = true)
    @field:NotNull(message = "ID can not be null")
    val id: Long?,

    /**
     * Dist
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     */
    @Schema(description = "产物", required = true)
    @field:NotBlank(message = "Dist can not be blank")
    var dist: String?
)

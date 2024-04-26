package top.fatweb.oxygen.api.param.tool

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import top.fatweb.oxygen.api.annotation.Trim
import top.fatweb.oxygen.api.entity.tool.ToolBase

/**
 * Remove favorite tool parameters
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
data class ToolFavoriteRemoveParam(
    /**
     * Username
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Trim
    @Schema(description = "用户名", required = true)
    @field: NotBlank(message = "Username cannot be blank")
    var username: String?,

    /**
     * Tool ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Trim
    @Schema(description = "工具唯一 ID", required = true)
    @field: NotBlank(message = "ToolId cannot be blank")
    @field: Pattern(
        regexp = "^[a-zA-Z-_][0-9a-zA-Z-_]{2,19}\$",
        message = "ToolId can only match '^[a-zA-Z-_][0-9a-zA-Z-_]{2,19}\$'"
    )
    var toolId: String?,

    /**
     * Platform
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolBase.Platform
     */
    @Schema(description = "平台")
    @field:NotNull(message = "Platform can not be null")
    val platform: ToolBase.Platform?
)
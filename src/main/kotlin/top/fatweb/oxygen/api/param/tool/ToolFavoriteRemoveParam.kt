package top.fatweb.oxygen.api.param.tool

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import top.fatweb.oxygen.api.annotation.Trim

/**
 * Remove favorite tool parameters
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
data class ToolFavoriteRemoveParam(
    /**
     * Author ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Trim
    @Schema(description = "作者 ID", required = true)
    @field: NotBlank(message = "AuthorId cannot be blank")
    var authorId: Long?,

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
)
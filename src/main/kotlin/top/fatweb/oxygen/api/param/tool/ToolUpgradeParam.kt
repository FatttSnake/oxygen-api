package top.fatweb.oxygen.api.param.tool

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

/**
 * Upgrade tool parameters
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Schema(description = "更新工具请求参数")
data class ToolUpgradeParam(
    /**
     * Tool ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "工具唯一 ID", required = true, example = "tool_a")
    @field: NotBlank(message = "ToolId can not be blank")
    @field: Pattern(
        regexp = "^[a-zA-Z-_][0-9a-zA-Z-_]{2,19}\$",
        message = "Ver can only match '^[a-zA-Z-_][0-9a-zA-Z-_]{2,19}\$'"
    )
    val toolId: String?,

    /**
     * Version
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "版本", required = true, example = "1.0.3")
    @field: NotBlank(message = "Ver can not be blank")
    @field: Pattern(regexp = "^\\d+\\.\\d+\\.\\d+\$", message = "Ver can only match '<number>.<number>.<number>'")
    val ver: String?
)

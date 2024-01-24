package top.fatweb.oxygen.api.param.tool

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull

/**
 * Update tool template parameters
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
data class ToolTemplateUpdateParam(
    /**
     * ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "ID", required = true)
    @field: NotNull(message = "ID can not be null")
    val id: Long?,

    /**
     * Name
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "名称")
    val name: String?,

    /**
     * Base ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "Base ID")
    val baseId: Long?,

    /**
     * Source
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "源码")
    val source: String?,

    /**
     * Enable
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "启用", allowableValues = ["true", "false"])
    val enable: Boolean?
)

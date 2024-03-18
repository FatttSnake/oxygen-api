package top.fatweb.oxygen.api.param.tool

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import top.fatweb.oxygen.api.annotation.Trim
import top.fatweb.oxygen.api.entity.tool.ToolBase

/**
 * Add tool base parameters
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Trim
data class ToolBaseAddParam(
    /**
     * Name
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Trim
    @Schema(description = "名称", required = true)
    @field: NotBlank(message = "Name can not be blank")
    var name: String?,

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

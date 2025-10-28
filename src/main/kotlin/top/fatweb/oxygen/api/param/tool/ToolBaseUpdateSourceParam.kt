package top.fatweb.oxygen.api.param.tool

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

/**
 * Update tool base source parameters
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.1.0
 */
@Schema(description = "更新工具基本源码请求参数")
data class ToolBaseUpdateSourceParam(
    /**
     * ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     */
    @field:Schema(description = "ID", required = true)
    @field:NotNull(message = "ID can not be null")
    val id: Long?,

    /**
     * Source
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     */
    @field:Schema(description = "源码", required = true)
    @field:NotBlank(message = "Source can not be blank")
    var source: String?
)

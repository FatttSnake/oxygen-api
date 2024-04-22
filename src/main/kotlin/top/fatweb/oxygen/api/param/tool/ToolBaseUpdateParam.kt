package top.fatweb.oxygen.api.param.tool

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import top.fatweb.oxygen.api.annotation.Trim

/**
 * Update tool base parameters
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Trim
data class ToolBaseUpdateParam(
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
    @Trim
    @Schema(description = "名称")
    @field: Pattern(regexp = "^.*\\S.*$", message = "Name can not be blank")
    var name: String?,

    /**
     * Source
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "源码")
    @field: Pattern(regexp = "^.*\\S.*$", message = "Source can not be blank")
    val source: String?,

    /**
     * Dist
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "产物")
    @field: Pattern(regexp = "^.*\\S.*$", message = "Dist can not be blank")
    val dist: String?
)

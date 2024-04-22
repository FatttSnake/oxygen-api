package top.fatweb.oxygen.api.param.tool

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import top.fatweb.oxygen.api.annotation.Trim

/**
 * Update tool parameters
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Trim
@Schema(description = "更新工具请求参数")
data class ToolUpdateParam(
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
     * Icon
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "图标")
    @field: Pattern(regexp = "^.*\\S.*$", message = "Icon can not be blank")
    val icon: String?,

    /**
     * Description
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "简介")
    val description: String?,

    /**
     * Keywords
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "关键词")
    @field:Size(min = 1, message = "Keywords can not be empty")
    val keywords: List<String>?,

    /**
     * Categories
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "类别")
    @field:Size(min = 1, message = "Categories can not be empty")
    val categories: List<Long>?,

    /**
     * Source
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "源码")
    @field: Pattern(regexp = "^.*\\S.*$", message = "Source can not be blank")
    val source: String?
)

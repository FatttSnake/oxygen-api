package top.fatweb.oxygen.api.param.tool

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern

/**
 * Create tool parameters
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Schema(description = "创建工具请求参数")
data class ToolCreateParam(
    /**
     * Name
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "名称", required = true)
    @field: NotBlank(message = "Name can not be blank")
    val name: String?,

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
     * Icon
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "图标", required = true)
    @field: NotBlank(message = "Icon can not be blank")
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
     * Version
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "版本", required = true, example = "1.0.3")
    @field: NotBlank(message = "Ver can not be blank")
    @field: Pattern(regexp = "^\\d+\\.\\d+\\.\\d+\$", message = "Ver can only match '<number>.<number>.<number>'")
    val ver: String?,

    /**
     * Template ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "模板 ID", required = true)
    @field: NotNull(message = "TemplateId can not be null")
    val templateId: Long?,

    /**
     * Privately
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "私有", allowableValues = ["true", "false"], defaultValue = "false")
    val privately: Boolean = false,

    /**
     * Keywords
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "关键词", required = true)
    @field: NotEmpty(message = "Keywords can not be empty")
    val keywords: List<String>,

    /**
     * Categories
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "类别", required = true)
    @field: NotEmpty(message = "Categories can not be empty")
    val categories: List<Long>
)

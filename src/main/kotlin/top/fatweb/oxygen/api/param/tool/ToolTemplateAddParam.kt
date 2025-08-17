package top.fatweb.oxygen.api.param.tool

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import top.fatweb.oxygen.api.annotation.ParamProcessor

/**
 * Add tool template parameters
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@ParamProcessor
@Schema(description = "新增工具模板请求参数")
data class ToolTemplateAddParam(
    /**
     * Name
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "名称", required = true)
    @field:NotBlank(message = "Name can not be blank")
    var name: String?,

    /**
     * Base ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "Base ID", required = true)
    @field:NotNull(message = "BaseId can not be null")
    val baseId: Long? = null,

    /**
     * Base version
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     */
    @Schema(description = "Base version", required = true)
    @field:NotNull(message = "Base version can not be null")
    val baseVersion: Long? = null,

    /**
     * Entry point
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "入口文件", required = true)
    @field:NotBlank(message = "EntryPoint can not be null")
    var entryPoint: String? = null,

    /**
     * Enable
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "启用", allowableValues = ["true", "false"], defaultValue = "true")
    val enable: Boolean = true
)

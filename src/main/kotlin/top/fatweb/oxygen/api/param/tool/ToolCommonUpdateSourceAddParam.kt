package top.fatweb.oxygen.api.param.tool

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.AssertTrue
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import top.fatweb.oxygen.api.annotation.ParamProcessor

/**
 * Update source - add file/directory parameters
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.2.0
 */
@ParamProcessor
@Schema(description = "更新源码-新增文件(目录)请求参数")
data class ToolCommonUpdateSourceAddParam(
    /**
     * Parent node ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.2.0
     */
    @field:Schema(description = "父节点 ID", required = true)
    @field:NotNull(message = "Parent node ID can not be null")
    val parentNode: Long?,

    /**
     * File/directory name
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.2.0
     */
    @field:Schema(description = "文件(目录)名", required = true)
    @field:NotBlank(message = "File/directory name can not be blank")
    @field:Pattern(
        regexp = "^[a-zA-Z0-9_\\-. ]{1,40}$",
        message = "File/directory name must be 1-40 characters and contain only alphanumeric, underscore, hyphen, dot, or space"
    )
    val fileName: String?,

    /**
     * Is directory node
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.2.0
     */
    @field:Schema(description = "是否为目录节点", required = true)
    @field:NotNull(message = "Is directory name can not be null")
    val dirNode: Boolean?
) {
    val fileNameValid: Boolean
        @AssertTrue(message = "File name must end with .jsx, .tsx, .js, .ts, .css, or .json when it is a file")
        get() = dirNode == true || fileName?.matches(Regex("^.*\\.(jsx|tsx|js|ts|css|json)$")) == true
}

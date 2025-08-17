package top.fatweb.oxygen.api.param.tool

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import top.fatweb.oxygen.api.annotation.ParamProcessor

/**
 * Update tool base parameters
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@ParamProcessor
@Schema(description = "更新工具基板请求参数")
data class ToolBaseUpdateParam(
    /**
     * ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "ID", required = true)
    @field:NotNull(message = "ID can not be null")
    val id: Long?,

    /**
     * Name
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "名称")
    @field:NotBlank(message = "Name can not be blank")
    var name: String?
)

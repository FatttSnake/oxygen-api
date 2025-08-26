package top.fatweb.oxygen.api.param.tool

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull

/**
 * Upgrade tool or template base version
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.1.0
 */
@Schema(description = "更新工具或模板基板版本请求参数")
data class ToolOrTemplateUpgradeBaseParam(
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
     * Tool base version
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     */
    @field:Schema(description = "基板版本")
    @field:NotNull(message = "Base version can not be null")
    val baseVersion: Long?
)

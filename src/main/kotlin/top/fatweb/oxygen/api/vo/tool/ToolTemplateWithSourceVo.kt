package top.fatweb.oxygen.api.vo.tool

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import io.swagger.v3.oas.annotations.media.Schema
import top.fatweb.oxygen.api.entity.tool.Platform
import java.time.LocalDateTime

/**
 * Tool template with source value object
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.1.0
 */
@Schema(description = "工具模板带源码返回参数")
data class ToolTemplateWithSourceVo(
    /**
     * ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     */
    @field:JsonSerialize(using = ToStringSerializer::class)
    val id: Long?,

    /**
     * Name
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     */
    @field:Schema(description = "名称")
    val name: String?,

    /**
     * Base
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     * @see ToolBaseVo
     */
    @field:Schema(description = "基板")
    val base: ToolBaseVo?,

    /**
     * Source
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     * @see ToolDataVo
     */
    @field:Schema(description = "源码")
    val source: ToolDataVo?,

    /**
     * Platform
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     * @see Platform
     */
    @field:Schema(description = "平台")
    val platform: Platform?,

    /**
     * Entry point
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     */
    @field:Schema(description = "入口文件")
    val entryPoint: String?,

    /**
     * Enable
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     */
    @field:Schema(description = "启用")
    val enable: Boolean?,

    /**
     * Create time
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     * @see LocalDateTime
     */
    @field:Schema(description = "创建时间", example = "1900-01-01T00:00:00.000Z")
    val createTime: LocalDateTime?,

    /**
     * Update time
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     * @see LocalDateTime
     */
    @field:Schema(description = "修改时间", example = "1900-01-01T00:00:00.000Z")
    val updateTime: LocalDateTime?
)

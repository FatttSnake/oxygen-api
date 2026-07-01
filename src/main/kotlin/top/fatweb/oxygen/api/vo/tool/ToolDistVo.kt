package top.fatweb.oxygen.api.vo.tool

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

/**
 * Tool dist value object
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.2.0
 */
@Schema(description = "工具产物返回参数")
data class ToolDistVo(
    /**
     * ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.2.0
     */
    @field:JsonSerialize(using = ToStringSerializer::class)
    val id: Long?,

    /**
     * File content
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.2.0
     */
    @field:Schema(description = "文件内容")
    val fileContent: String?,

    /**
     * File size
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.2.0
     */
    @field:Schema(description = "文件大小")
    @field:JsonSerialize(using = ToStringSerializer::class)
    val fileSize: Long?,

    /**
     * Create time
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.2.0
     * @see LocalDateTime
     */
    @field:Schema(description = "创建时间", example = "1900-01-01T00:00:00.000Z")
    val createTime: LocalDateTime?,

    /**
     * Update time
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.2.0
     * @see LocalDateTime
     */
    @field:Schema(description = "修改时间", example = "1900-01-01T00:00:00.000Z")
    val updateTime: LocalDateTime?
)

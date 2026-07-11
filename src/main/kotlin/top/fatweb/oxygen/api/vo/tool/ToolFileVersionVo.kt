package top.fatweb.oxygen.api.vo.tool

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

/**
 * Tool file version value object
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.3.0
 */
@Schema(description = "工具文件版本返回参数")
data class ToolFileVersionVo(
    /**
     * ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     */
    @field:JsonSerialize(using = ToStringSerializer::class)
    val id: Long?,

    /**
     * Node ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     */
    @field:Schema(description = "节点 ID")
    @field:JsonSerialize(using = ToStringSerializer::class)
    val nodeId: Long?,

    /**
     * File version
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     */
    @field:Schema(description = "文件版本")
    val ver: Int?,

    /**
     * File content
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     */
    @field:Schema(description = "文件内容")
    val fileContent: String?,

    /**
     * File size
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     */
    @field:Schema(description = "文件大小")
    @field:JsonSerialize(using = ToStringSerializer::class)
    val fileSize: Long?,

    /**
     * Create time
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     * @see LocalDateTime
     */
    @field:Schema(description = "创建时间", example = "1900-01-01T00:00:00.000Z")
    val createTime: LocalDateTime?,

    /**
     * Update time
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     * @see LocalDateTime
     */
    @field:Schema(description = "修改时间", example = "1900-01-01T00:00:00.000Z")
    val updateTime: LocalDateTime?
)

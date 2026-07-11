package top.fatweb.oxygen.api.vo.tool

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

/**
 * Tool source value object
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.3.0
 */
@Schema(description = "工具源码返回参数")
data class ToolSourceVo(
    /**
     * ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     */
    @field:JsonSerialize(using = ToStringSerializer::class)
    val id: Long?,

    /**
     * Root node ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     */
    @field:Schema(description = "根节点 ID")
    @field:JsonSerialize(using = ToStringSerializer::class)
    val rootId: Long?,

    /**
     * Parent node ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     */
    @field:Schema(description = "父节点 ID")
    @field:JsonSerialize(using = ToStringSerializer::class)
    val parentId: Long?,

    /**
     * Filename
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     */
    @field:Schema(description = "文件名")
    val fileName: String?,

    /**
     * Is root node
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     */
    @field:Schema(description = "是否为根节点")
    val rootNode: Boolean?,

    /**
     * Is directory node
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     */
    @field:Schema(description = "是否为目录节点")
    val dirNode: Boolean?,

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
    val updateTime: LocalDateTime?,

    /**
     * Lastest file version value object
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     * @see ToolFileVersionVo
     */
    @field:Schema(description = "最新文件版本")
    val latestFileVersion: ToolFileVersionVo?
)

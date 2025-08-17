package top.fatweb.oxygen.api.entity.tool

import com.baomidou.mybatisplus.annotation.*
import java.io.Serializable
import java.time.LocalDateTime

/**
 * Tool base entity
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@TableName("t_b_tool_base")
class ToolBase : Serializable {
    /**
     * ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @TableId("id")
    var id: Long? = null

    /**
     * Name
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @TableField("name")
    var name: String? = null

    /**
     * Platform
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see Platform
     */
    @TableField("platform")
    var platform: Platform? = null

    /**
     * Create time
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see LocalDateTime
     */
    @TableField("create_time", fill = FieldFill.INSERT)
    var createTime: LocalDateTime? = null

    /**
     * Update time
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see LocalDateTime
     */
    @TableField("update_time", fill = FieldFill.INSERT_UPDATE)
    var updateTime: LocalDateTime? = null

    /**
     * Deleted
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @TableField("deleted")
    @TableLogic
    var deleted: Long? = null

    /**
     * Version
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @TableField("version")
    @Version
    var version: Int? = null

    /**
     * Source
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @TableField(exist = false)
    var source: ToolData? = null

    /**
     * Dist
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @TableField(exist = false)
    var dist: ToolData? = null

    /**
     * Version list
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     */
    @TableField(exist = false)
    var versions: List<Long>? = null

    /**
     * Base version
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     */
    @TableField(exist = false)
    var baseVersion: Long? = null

    override fun toString(): String {
        return "ToolBase(id=$id, name=$name, platform=$platform, createTime=$createTime, updateTime=$updateTime, deleted=$deleted, version=$version, source=$source, dist=$dist, versions=$versions, baseVersion=$baseVersion)"
    }
}

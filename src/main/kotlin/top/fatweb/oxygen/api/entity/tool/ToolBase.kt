package top.fatweb.oxygen.api.entity.tool

import com.baomidou.mybatisplus.annotation.*
import com.fasterxml.jackson.annotation.JsonValue
import java.time.LocalDateTime

/**
 * Tool base entity
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@TableName("t_b_tool_base")
class ToolBase {
    /**
     * Platform enum
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    enum class Platform(@field:EnumValue @field:JsonValue val code: String) {
        WEB("WEB"), DESKTOP("DESKTOP"), ANDROID("ANDROID")
    }

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
     * Source ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @TableField("source_id")
    var sourceId: Long? = null

    /**
     * Dist ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @TableField("dist_id")
    var distId: Long? = null

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
     * Has compiled
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @TableField("compiled")
    var compiled: Int? = null

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
     * Dist data
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @TableField(exist = false)
    var distData: String? = null

    override fun toString(): String {
        return "ToolBase(id=$id, name=$name, sourceId=$sourceId, distId=$distId, platform=$platform, compiled=$compiled, createTime=$createTime, updateTime=$updateTime, deleted=$deleted, version=$version, source=$source, dist=$dist, distData=$distData)"
    }
}
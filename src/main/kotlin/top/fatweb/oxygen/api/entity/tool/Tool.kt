package top.fatweb.oxygen.api.entity.tool

import com.baomidou.mybatisplus.annotation.*
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler
import com.fasterxml.jackson.annotation.JsonValue
import top.fatweb.oxygen.api.entity.permission.User
import java.time.LocalDateTime

/**
 * Tool entity
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@TableName("t_b_tool_main", autoResultMap = true)
class Tool {
    /**
     * Tool review type enum
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    enum class ReviewType(@field:EnumValue @field:JsonValue val code: String) {
        NONE("NONE"), PASS("PASS"), REJECT("REJECT")
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
     * Tool ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @TableField("tool_id")
    var toolId: String? = null

    /**
     * Description
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @TableField("description")
    var description: String? = null

    /**
     * Base ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @TableField("base_id")
    var baseId: Long? = null

    /**
     * Author ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @TableField("author_id")
    var authorId: Long? = null

    /**
     * Version of tool
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @TableField("ver")
    var ver: String? = null

    /**
     * Privately
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @TableField("privately")
    var privately: Int? = null

    /**
     * Keywords
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @TableField("keywords", typeHandler = JacksonTypeHandler::class)
    var keywords: List<String>? = null

    /**
     * Source code ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @TableField("source_id")
    var sourceId: Long? = null

    /**
     * Compile product ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @TableField("dist_id")
    var distId: Long? = null

    /**
     * Entry point
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @TableField("entry_point")
    var entryPoint: String? = null

    /**
     * Publish
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @TableField("publish")
    var publish: Int? = null

    /**
     * Review
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @TableField("review")
    var review: ReviewType? = null

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
     * Author
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @TableField(exist = false)
    var author: User? = null

    /**
     * Base
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @TableField(exist = false)
    var base: ToolBase? = null

    /**
     * Categories
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @TableField(exist = false)
    var categories: List<ToolCategory>? = null

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

    override fun toString(): String {
        return "Tool(id=$id, name=$name, toolId=$toolId, description=$description, baseId=$baseId, authorId=$authorId, ver=$ver, privately=$privately, keywords=$keywords, sourceId=$sourceId, distId=$distId, entryPoint=$entryPoint, publish=$publish, review=$review, createTime=$createTime, updateTime=$updateTime, deleted=$deleted, version=$version, author=$author, categories=$categories, source=$source, dist=$dist)"
    }
}
package top.fatweb.oxygen.api.entity.tool

import com.baomidou.mybatisplus.annotation.*
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler

/**
 * Tool entity
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@TableName("t_b_tool_main", autoResultMap = true)
class Tool {
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
     * Author
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @TableField("author")
    var author: Long? = null

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
     * Keyword
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @TableField("keyword", typeHandler = JacksonTypeHandler::class)
    var keyword: List<String>? = null

    /**
     * Source code
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @TableField("source")
    var source: Long? = null

    /**
     * Compile product
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    var dist: Long? = null

    /**
     * Publish
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    var publish: Int? = null

    /**
     * Review
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    var review: Int? = null

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

    override fun toString(): String {
        return "Tool(id=$id, name=$name, toolId=$toolId, description=$description, author=$author, ver=$ver, privately=$privately, keyword=$keyword, source=$source, dist=$dist, publish=$publish, review=$review, deleted=$deleted, version=$version)"
    }
}
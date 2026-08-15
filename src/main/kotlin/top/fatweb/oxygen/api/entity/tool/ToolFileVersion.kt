package top.fatweb.oxygen.api.entity.tool

import com.baomidou.mybatisplus.annotation.*
import java.io.Serializable
import java.time.LocalDateTime

/**
 * Tool file version entity
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.3.0
 */
@TableName("t_b_tool_file_version")
class ToolFileVersion : Serializable {
    /**
     * ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     */
    @TableId("id")
    var id: Long? = null

    /**
     * Source node ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     */
    @TableField("node_id")
    var nodeId: Long? = null

    /**
     * File version
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     */
    @TableField("ver")
    var ver: Int? = null

    /**
     * File SHA-256 key
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     */
    @TableField("file_hash")
    var fileHash: String? = null

    /**
     * File size
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     */
    @TableField("file_size")
    var fileSize: Long? = null

    /**
     * Create time
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     * @see LocalDateTime
     */
    @TableField("create_time", fill = FieldFill.INSERT)
    var createTime: LocalDateTime? = null

    /**
     * Update time
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     * @see LocalDateTime
     */
    @TableField("update_time", fill = FieldFill.INSERT_UPDATE)
    var updateTime: LocalDateTime? = null

    /**
     * Version
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     */
    @TableField("version")
    @Version
    var version: Int? = null

    /**
     * File content
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     */
    @TableField(exist = false)
    var fileContent: String? = null

    override fun toString(): String {
        return "ToolFileVersion(id=$id, nodeId=$nodeId, ver=$ver, fileHash=$fileHash, fileSize=$fileSize, createTime=$createTime, updateTime=$updateTime, version=$version, fileContent=$fileContent)"
    }
}

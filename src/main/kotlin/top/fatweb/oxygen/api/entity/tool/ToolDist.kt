package top.fatweb.oxygen.api.entity.tool

import com.baomidou.mybatisplus.annotation.FieldFill
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import com.baomidou.mybatisplus.annotation.Version
import java.io.Serializable
import java.time.LocalDateTime

/**
 * Tool dist entity
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.3.0
 */
@TableName("t_b_tool_dist")
class ToolDist : Serializable {
    /**
     * ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.3.0
     */
    @TableId("id")
    var id: Long? = null

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
        return "ToolDist(id=$id, fileHash=$fileHash, fileSize=$fileSize, createTime=$createTime, updateTime=$updateTime, version=$version, fileContent=$fileContent)"
    }
}

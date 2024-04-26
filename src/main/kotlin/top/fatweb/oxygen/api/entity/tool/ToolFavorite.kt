package top.fatweb.oxygen.api.entity.tool

import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableLogic
import com.baomidou.mybatisplus.annotation.TableName
import com.baomidou.mybatisplus.annotation.Version
import java.io.Serializable

@TableName("t_b_tool_favorite")
class ToolFavorite : Serializable {
    /**
     * ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @TableId("id")
    var id: Long? = null

    /**
     * User ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @TableField("user_id")
    var userId: Long? = null

    /**
     * Username
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @TableField("username")
    var username: String? = null

    /**
     * Tool ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @TableField("tool_id")
    var toolId: String? = null

    /**
     * Platform
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolBase.Platform
     */
    @TableField("platform")
    var platform: ToolBase.Platform? = null

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
        return "ToolFavorite(id=$id, userId=$userId, username=$username, toolId=$toolId, platform=$platform, deleted=$deleted, version=$version)"
    }
}
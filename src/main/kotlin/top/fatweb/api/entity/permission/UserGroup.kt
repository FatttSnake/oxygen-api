package top.fatweb.api.entity.permission

import com.baomidou.mybatisplus.annotation.*
import java.io.Serializable

/**
 * User group intermediate entity
 *
 * @author FatttSnake
 * @since 1.0.0
 */
@TableName("t_user_group")
class UserGroup : Serializable {

    @TableId("id")
    var id: Long? = null

    /**
     * 用户
     */
    @TableField("user_id")
    var userId: Long? = null

    /**
     * 用户组
     */
    @TableField("group_id")
    var groupId: Long? = null

    @TableField("deleted")
    @TableLogic
    var deleted: Long? = null

    @TableField("version")
    @Version
    var version: Int? = null

    override fun toString(): String {
        return "UserGroup(id=$id, userId=$userId, groupId=$groupId, deleted=$deleted, version=$version)"
    }
}

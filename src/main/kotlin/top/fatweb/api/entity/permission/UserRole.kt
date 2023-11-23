package top.fatweb.api.entity.permission

import com.baomidou.mybatisplus.annotation.*
import java.io.Serializable

/**
 * User role intermediate entity
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@TableName("t_user_role")
class UserRole : Serializable {

    @TableId("id")
    var id: Long? = null

    /**
     * 用户
     */
    @TableField("user_id")
    var userId: Long? = null

    /**
     * 角色
     */
    @TableField("role_id")
    var roleId: Long? = null

    @TableField("deleted")
    @TableLogic
    var deleted: Long? = null

    @TableField("version")
    @Version
    var version: Int? = null

    override fun toString(): String {
        return "UserRole(id=$id, userId=$userId, roleId=$roleId, deleted=$deleted, version=$version)"
    }
}

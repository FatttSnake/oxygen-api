package top.fatweb.api.entity.permission

import com.baomidou.mybatisplus.annotation.*
import java.io.Serializable

/**
 * Role group intermediate entity
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@TableName("t_role_group")
class RoleGroup : Serializable {

    @TableId("id")
    var id: Long? = null

    /**
     * 角色
     */
    @TableField("role_id")
    var roleId: Long? = null

    /**
     * 群组
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
        return "RoleGroup(id=$id, roleId=$roleId, groupId=$groupId, deleted=$deleted, version=$version)"
    }
}

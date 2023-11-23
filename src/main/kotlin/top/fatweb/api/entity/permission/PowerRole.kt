package top.fatweb.api.entity.permission

import com.baomidou.mybatisplus.annotation.*
import java.io.Serializable

/**
 * Power role intermediate entity
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@TableName("t_power_role")
class PowerRole : Serializable {

    @TableId("id")
    var id: Long? = null

    /**
     * 权限
     */
    @TableField("power_id")
    var powerId: Long? = null

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
        return "PowerRole(id=$id, powerId=$powerId, roleId=$roleId, deleted=$deleted, version=$version)"
    }
}

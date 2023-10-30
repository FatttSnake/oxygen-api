package top.fatweb.api.entity.permission

import com.baomidou.mybatisplus.annotation.*
import java.io.Serializable

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author FatttSnake
 * @since 2023-10-25
 */
@TableName("t_role")
class Role : Serializable {

    @TableId("id")
    var id: Long? = null

    /**
     * 角色名
     */
    @TableField("name")
    var name: String? = null

    /**
     * 启用
     */
    @TableField("enable")
    var enable: Int? = null

    @TableField("deleted")
    @TableLogic
    var deleted: Long? = null

    @TableField("version")
    @Version
    var version: Int? = null

    @TableField(exist = false)
    var menus: List<Menu>? = null

    @TableField(exist = false)
    var elements: List<Element>? = null

    @TableField(exist = false)
    var operations: List<Operation>? = null

    @TableField(exist = false)
    var powers: List<Power>? = null

    override fun toString(): String {
        return "Role(id=$id, name=$name, enable=$enable, deleted=$deleted, version=$version, menus=$menus, elements=$elements, operations=$operations, powers=$powers)"
    }
}

package top.fatweb.api.entity.permission

import com.baomidou.mybatisplus.annotation.*
import java.io.Serializable
import java.time.LocalDateTime

/**
 * Role entity
 *
 * @author FatttSnake
 * @since 1.0.0
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

    /**
     * 创建时间
     */
    @TableField("create_time", fill = FieldFill.INSERT)
    var createTime: LocalDateTime? = null

    /**
     * 修改时间
     */
    @TableField("update_time", fill = FieldFill.INSERT_UPDATE)
    var updateTime: LocalDateTime? = null

    @TableField("deleted")
    @TableLogic
    var deleted: Long? = null

    @TableField("version")
    @Version
    var version: Int? = null

    @TableField(exist = false)
    var modules: List<Module>? = null

    @TableField(exist = false)
    var menus: List<Menu>? = null

    @TableField(exist = false)
    var elements: List<Element>? = null

    @TableField(exist = false)
    var operations: List<Operation>? = null

    @TableField(exist = false)
    var powers: List<Power>? = null

    override fun toString(): String {
        return "Role(id=$id, name=$name, enable=$enable, deleted=$deleted, version=$version, modules=$modules, menus=$menus, elements=$elements, operations=$operations, powers=$powers)"
    }
}

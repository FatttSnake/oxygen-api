package top.fatweb.api.entity.permission

import com.baomidou.mybatisplus.annotation.*
import java.io.Serializable
import java.time.LocalDateTime

/**
 * Group entity
 *
 * @author FatttSnake
 * @since 1.0.0
 */
@TableName("t_group")
class Group : Serializable {

    @TableId("id")
    var id: Long? = null

    /**
     * 用户组名
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
    var roles: List<Role>? = null

    override fun toString(): String {
        return "Group(id=$id, name=$name, enable=$enable, deleted=$deleted, version=$version, roles=$roles)"
    }
}

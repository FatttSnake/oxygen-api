package top.fatweb.api.entity.permission

import com.baomidou.mybatisplus.annotation.*
import java.io.Serializable

/**
 * <p>
 * 用户组
 * </p>
 *
 * @author FatttSnake
 * @since 2023-10-25
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

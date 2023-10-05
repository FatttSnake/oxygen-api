package top.fatweb.api.entity.permission

import com.baomidou.mybatisplus.annotation.*
import java.io.Serializable

/**
 * <p>
 * 用户
 * </p>
 *
 * @author FatttSnake
 * @since 2023-10-04
 */
@TableName("t_user")
class User : Serializable {

    @TableId("id")
    var id: Long? = null

    /**
     * 用户名
     */
    @TableField("username")
    var username: String? = null

    /**
     * 密码
     */
    @TableField("password")
    var password: String? = null

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

    override fun toString(): String {
        return "User{" +
                "id=" + id +
                ", username=" + username +
                ", password=" + password +
                ", enable=" + enable +
                ", deleted=" + deleted +
                ", version=" + version +
                "}"
    }
}

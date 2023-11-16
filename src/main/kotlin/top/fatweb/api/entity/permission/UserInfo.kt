package top.fatweb.api.entity.permission

import com.baomidou.mybatisplus.annotation.*
import java.io.Serializable
import java.time.LocalDateTime

/**
 * User information entity
 *
 * @author FatttSnake
 * @since 1.0.0
 */
@TableName("t_user_info")
class UserInfo : Serializable {

    @TableId("id")
    var id: Long? = null

    @TableField("user_id")
    var userId: Long? = null

    /**
     * 昵称
     */
    @TableField("nickname")
    var nickname: String? = null

    /**
     * 头像
     */
    @TableField("avatar")
    var avatar: String? = null

    /**
     * 邮箱
     */
    @TableField("email")
    var email: String? = null

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

    override fun toString(): String {
        return "UserInfo(id=$id, userId=$userId, nickname=$nickname, avatar=$avatar, email=$email, createTime=$createTime, updateTime=$updateTime, deleted=$deleted, version=$version)"
    }
}

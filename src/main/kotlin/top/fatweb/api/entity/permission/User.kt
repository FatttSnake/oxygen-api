package top.fatweb.api.entity.permission

import com.baomidou.mybatisplus.annotation.*
import java.io.Serializable
import java.time.LocalDateTime

/**
 * User entity
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@TableName("t_user")
class User() : Serializable {
    constructor(id: Long?, username: String, password: String, enable: Boolean = true) : this() {
        this.id = id
        this.username = username
        this.password = password
        this.enable = if (enable) 1 else 0
    }

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
     * 锁定
     */
    @TableField("locking")
    var locking: Int? = null

    /**
     * 过期时间
     */
    @TableField("expiration")
    var expiration: LocalDateTime? = null

    /**
     * 认证过期时间
     */
    @TableField("credentials_expiration")
    var credentialsExpiration: LocalDateTime? = null

    /**
     * 启用
     */
    @TableField("enable")
    var enable: Int? = null

    /**
     * 当前登录时间
     */
    @TableField("current_login_time")
    var currentLoginTime: LocalDateTime? = null

    /**
     * 当前登录 IP
     */
    @TableField("current_login_ip")
    var currentLoginIp: String? = null

    /**
     * 上次登录时间
     */
    @TableField("last_login_time")
    var lastLoginTime: LocalDateTime? = null

    /**
     * 上次登录 IP
     */
    @TableField("last_login_ip")
    var lastLoginIp: String? = null

    /**
     * 创建时间
     */
    @TableField("create_time")
    var createTime: LocalDateTime? = null

    /**
     * 修改时间
     */
    @TableField("update_time")
    var updateTime: LocalDateTime? = null

    @TableField("deleted")
    @TableLogic
    var deleted: Long? = null

    @TableField("version")
    @Version
    var version: Int? = null

    @TableField(exist = false)
    var userInfo: UserInfo? = null

    @TableField(exist = false)
    var roles: List<Role>? = null

    @TableField(exist = false)
    var groups: List<Group>? = null

    @TableField(exist = false)
    var modules: List<Module>? = null

    @TableField(exist = false)
    var menus: List<Menu>? = null

    @TableField(exist = false)
    var funcs: List<Func>? = null

    @TableField(exist = false)
    var operations: List<Operation>? = null

    override fun toString(): String {
        return "User(id=$id, username=$username, password=$password, locking=$locking, expiration=$expiration, credentialsExpiration=$credentialsExpiration, enable=$enable, currentLoginTime=$currentLoginTime, currentLoginIp=$currentLoginIp, lastLoginTime=$lastLoginTime, lastLoginIp=$lastLoginIp, createTime=$createTime, updateTime=$updateTime, deleted=$deleted, version=$version, userInfo=$userInfo, roles=$roles, groups=$groups, modules=$modules, menus=$menus, funcs=$funcs, operations=$operations)"
    }
}

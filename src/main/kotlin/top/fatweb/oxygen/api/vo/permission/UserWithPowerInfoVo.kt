package top.fatweb.oxygen.api.vo.permission

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import io.swagger.v3.oas.annotations.media.Schema
import top.fatweb.oxygen.api.vo.permission.base.*
import java.time.LocalDateTime

/**
 * User with power information value object
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Schema(description = "用户权限信息返回参数")
data class UserWithPowerInfoVo(
    /**
     * ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @JsonSerialize(using = ToStringSerializer::class)
    val id: Long?,

    /**
     * Username
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "用户名", example = "User")
    val username: String?,

    /**
     * Verified
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "已验证", example = "true")
    val verified: Boolean?,

    /**
     * Locking
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "是否锁定", example = "false")
    val locking: Boolean?,

    /**
     * Expiration time
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see LocalDateTime
     */
    @Schema(description = "过期时间", example = "1900-01-01T00:00:00.000Z")
    val expiration: LocalDateTime?,

    /**
     * Credentials expiration time
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see LocalDateTime
     */
    @Schema(description = "认证过期时间", example = "1900-01-01T00:00:00.000Z")
    val credentialsExpiration: LocalDateTime?,

    /**
     * Enable
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "是否启用", example = "true")
    val enable: Boolean?,

    /**
     * Current login time
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see LocalDateTime
     */
    @Schema(description = "当前登录时间", example = "1900-01-01T00:00:00.000Z")
    val currentLoginTime: LocalDateTime?,

    /**
     * Last login time
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see LocalDateTime
     */
    @Schema(description = "当前登录 IP", example = "1.1.1.1")
    val currentLoginIp: String?,

    /**
     * Last login time
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see LocalDateTime
     */
    @Schema(description = "最后登录时间", example = "1900-01-01T00:00:00.000Z")
    val lastLoginTime: LocalDateTime?,

    /**
     * Last login IP
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "最后登录 IP", example = "1.1.1.1")
    val lastLoginIp: String?,

    /**
     * Create time
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see LocalDateTime
     */
    @Schema(description = "创建时间", example = "1900-01-01T00:00:00.000Z")
    val createTime: LocalDateTime?,

    /**
     * Update time
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see LocalDateTime
     */
    @Schema(description = "修改时间", example = "1900-01-01T00:00:00.000Z")
    val updateTime: LocalDateTime?,

    /**
     * User information object
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see UserInfoVo
     */
    @Schema(description = "用户资料")
    val userInfo: UserInfoVo?,

    /**
     * List of ModuleVo object
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "模块列表")
    val modules: List<ModuleVo>?,

    /**
     * List of MenuVo object
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "菜单列表")
    val menus: List<MenuVo>?,

    /**
     * List of FuncVo object
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "功能列表")
    val funcs: List<FuncVo>?,

    /**
     * List of OperationVo object
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "操作列表")
    val operations: List<OperationVo>?
)
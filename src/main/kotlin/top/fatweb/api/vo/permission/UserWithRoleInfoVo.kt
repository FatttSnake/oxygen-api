package top.fatweb.api.vo.permission

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

/**
 * User with role information value object
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Schema(description = "用户角色信息返回参数")
data class UserWithRoleInfoVo(
    @JsonSerialize(using = ToStringSerializer::class)
    val id: Long?,

    @Schema(description = "用户名", example = "User")
    val username: String?,

    @Schema(description = "是否锁定", example = "false")
    val locking: Boolean?,

    @Schema(description = "过期时间", example = "1900-01-01T00:00:00.000Z")
    val expiration: LocalDateTime?,

    @Schema(description = "认证过期时间", example = "1900-01-01T00:00:00.000Z")
    val credentialsExpiration: LocalDateTime?,

    @Schema(description = "是否启用", example = "true")
    val enable: Boolean?,

    @Schema(description = "当前登录时间", example = "1900-01-01T00:00:00.000Z")
    val currentLoginTime: LocalDateTime?,

    @Schema(description = "当前登录 IP", example = "1.1.1.1")
    val currentLoginIp: String?,

    @Schema(description = "最后登录时间", example = "1900-01-01T00:00:00.000Z")
    val lastLoginTime: LocalDateTime?,

    @Schema(description = "最后登录 IP", example = "1.1.1.1")
    val lastLoginIp: String?,

    @Schema(description = "创建时间", example = "1900-01-01T00:00:00.000Z")
    val createTime: LocalDateTime?,

    @Schema(description = "修改时间", example = "1900-01-01T00:00:00.000Z")
    val updateTime: LocalDateTime?,

    @Schema(description = "用户资料")
    val userInfo: UserInfoVo?,

    @Schema(description = "角色列表")
    val roles: List<RoleVo>?,

    @Schema(description = "用户组列表")
    val groups: List<GroupVo>?
)
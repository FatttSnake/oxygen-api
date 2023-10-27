package top.fatweb.api.vo.authentication

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "获取用户信息返回参数")
data class UserInfoVo(
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

    @Schema(description = "最后登录时间", example = "1900-01-01T00:00:00.000Z")
    val lastLoginTime: LocalDateTime?,

    @Schema(description = "最后登录 IP", example = "1.1.1.1")
    val lastLoginIp: String?,

    @Schema(description = "创建时间", example = "1900-01-01T00:00:00.000Z")
    val createTime: LocalDateTime?,

    @Schema(description = "修改时间", example = "1900-01-01T00:00:00.000Z")
    val updateTime: LocalDateTime?,

    @Schema(description = "菜单列表")
    val menus: List<MenuVo>?,

    @Schema(description = "页面元素列表")
    val elements: List<ElementVo>?,

    @Schema(description = "功能列表")
    val operations: List<OperationVo>?
)
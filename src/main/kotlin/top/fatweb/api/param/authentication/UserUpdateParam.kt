package top.fatweb.api.param.authentication

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

@Schema(description = "用户更新请求参数")
data class UserUpdateParam(
    @Schema(description = "用户 ID")
    @field:NotNull(message = "ID can not be null")
    val id: Long?,

    @Schema(description = "用户名")
    val username: String?,

    @Schema(description = "锁定")
    val locking: Boolean = false,

    @Schema(description = "过期时间")
    val expiration: LocalDateTime?,

    @Schema(description = "认证过期时间")
    val credentialsExpiration: LocalDateTime?,

    @Schema(description = "启用")
    val enable: Boolean = true,

    @Schema(description = "昵称")
    val nickname: String?,

    @Schema(description = "头像")
    val avatar: String?,

    @Schema(description = "邮箱")
    val email: String?,

    @Schema(description = "角色 ID 列表")
    val roleIds: List<Long>?,

    @Schema(description = "用户组 ID 列表")
    val groupIds: List<Long>?
)

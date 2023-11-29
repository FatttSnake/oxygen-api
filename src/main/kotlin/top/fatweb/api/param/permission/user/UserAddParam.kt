package top.fatweb.api.param.permission.user

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import java.time.LocalDateTime

@Schema(description = "用户添加请求参数")
data class UserAddParam(
    @Schema(description = "用户名")
    @field:NotBlank(message = "Username can not be blank")
    val username: String?,

    @Schema(description = "密码(为空自动生成随机密码)")
    val password: String?,

    @Schema(description = "锁定", allowableValues = ["true", "false"], defaultValue = "false")
    val locking: Boolean = false,

    @Schema(description = "过期时间")
    val expiration: LocalDateTime?,

    @Schema(description = "认证过期时间")
    val credentialsExpiration: LocalDateTime?,

    @Schema(description = "启用", allowableValues = ["true", "false"], defaultValue = "true")
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

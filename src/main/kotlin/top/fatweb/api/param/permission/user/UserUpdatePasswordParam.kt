package top.fatweb.api.param.permission.user

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

@Schema(description = "用户更改密码请求参数")
data class UserUpdatePasswordParam(
    @Schema(description = "用户 ID")
    @field:NotNull(message = "ID can not be null")
    val id: Long?,

    @Schema(description = "新密码")
    @field:NotBlank(message = "Password can not be blank")
    val password: String?,

    @Schema(description = "认证过期时间")
    val credentialsExpiration: LocalDateTime?
)

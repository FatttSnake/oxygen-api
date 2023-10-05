package top.fatweb.api.entity.param

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import java.io.Serializable

class LoginParam : Serializable {

    @Schema(description = "用户名", example = "test", required = true)
    @NotBlank(message = "Username can not be blank")
    val username: String? = null

    @Schema(description = "密码", example = "test123456", required = true)
    @NotBlank(message = "Password can not be blank")
    val password: String? = null
}
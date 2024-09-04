package top.fatweb.oxygen.api.param.permission.user

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import top.fatweb.oxygen.api.annotation.Trim

/**
 * Update user information parameters
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Trim
@Schema(description = "更新用户信息请求参数")
data class UserInfoUpdateParam(
    /**
     * Avatar base64
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "头像")
    val avatar: String?,

    /**
     * Nickname
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Trim
    @Schema(description = "昵称", example = "QwQ")
    @field:NotBlank(message = "Nickname can not be blank")
    @field:Size(min = 3, max = 20, message = "Nickname must be 3-20 characters")
    var nickname: String?
)

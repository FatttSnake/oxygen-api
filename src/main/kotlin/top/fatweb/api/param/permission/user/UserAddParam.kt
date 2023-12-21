package top.fatweb.api.param.permission.user

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import java.time.LocalDateTime

/**
 * Add user parameters
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Schema(description = "用户添加请求参数")
data class UserAddParam(
    /**
     * Username
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "用户名")
    @field:NotBlank(message = "Username can not be blank")
    val username: String?,

    /**
     * Password
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "密码(为空自动生成随机密码)")
    val password: String?,

    /**
     * Verified
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "是否已验证")
    val verified: Boolean = false,

    /**
     * Locking
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "锁定", allowableValues = ["true", "false"], defaultValue = "false")
    val locking: Boolean = false,

    /**
     * Expiration time
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see LocalDateTime
     */
    @Schema(description = "过期时间")
    val expiration: LocalDateTime?,

    /**
     * Credentials expiration time
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see LocalDateTime
     */
    @Schema(description = "认证过期时间")
    val credentialsExpiration: LocalDateTime?,

    /**
     * Enable
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "启用", allowableValues = ["true", "false"], defaultValue = "true")
    val enable: Boolean = true,

    /**
     * Nickname
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "昵称")
    val nickname: String?,

    /**
     * Avatar base63
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "头像")
    val avatar: String?,

    /**
     * Email
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "邮箱")
    @Pattern(regexp = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*\$", message = "Illegal email address")
    val email: String?,

    /**
     * List of role IDs
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "角色 ID 列表")
    val roleIds: List<Long>?,

    /**
     * List of group IDs
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "用户组 ID 列表")
    val groupIds: List<Long>?
)

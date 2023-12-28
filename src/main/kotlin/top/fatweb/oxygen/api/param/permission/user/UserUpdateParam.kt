package top.fatweb.oxygen.api.param.permission.user

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import java.time.LocalDateTime

/**
 * Update user parameters
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Schema(description = "用户更新请求参数")
data class UserUpdateParam(
    /**
     * ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "用户 ID", required = true)
    @field:NotNull(message = "ID can not be null")
    val id: Long?,

    /**
     * Username
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "用户名", required = true, example = "User_1")
    @field:NotBlank(message = "Username can not be blank")
    val username: String?,

    /**
     * Verified
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "是否已验证", allowableValues = ["true", "false"], defaultValue = "false", example = "false")
    val verified: Boolean = false,

    /**
     * Locking
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "锁定", allowableValues = ["true", "false"], defaultValue = "false", example = "false")
    val locking: Boolean = false,

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
    @Schema(description = "启用", allowableValues = ["true", "false"], defaultValue = "true", example = "true")
    val enable: Boolean = true,

    /**
     * Nickname
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "昵称", required = true, example = "Nickname_1")
    @field:NotBlank(message = "Nickname can not be blank")
    val nickname: String?,

    /**
     * Avatar base64
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
    @Schema(description = "邮箱", required = true, example = "user@email.com")
    @NotBlank(message = "Email can not be blank")
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

package top.fatweb.api.vo.permission

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

/**
 * User information value object
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Schema(description = "用户资料返回参数")
data class UserInfoVo(
    @JsonSerialize(using = ToStringSerializer::class)
    val id: Long?,

    @Schema(description = "用户ID")
    @JsonSerialize(using = ToStringSerializer::class)
    val userId: Long?,

    @Schema(description = "昵称", example = "User")
    val nickname: String?,

    @Schema(description = "头像")
    val avatar: String?,

    @Schema(description = "邮箱", example = "user@fatweb.top")
    val email: String?,

    @Schema(description = "创建时间", example = "1900-01-01T00:00:00.000Z")
    val createTime: LocalDateTime?,

    @Schema(description = "修改时间", example = "1900-01-01T00:00:00.000Z")
    val updateTime: LocalDateTime?
)

package top.fatweb.oxygen.api.vo.permission

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

/**
 * Login value object
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Schema(description = "登录返回参数")
data class LoginVo(
    /**
     * Refresh Token
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     */
    @Schema(
        description = "Refresh Token",
        example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiJkYTllYjFkYmVmZDQ0OWRkOThlOGNjNzZlNzZkMDgyNSIsInN1YiI6IjE3MDk5ODYwNTg2Nzk5NzU5MzgiLCJpc3MiOiJGYXRXZWIiLCJpYXQiOjE2OTY1MjgxMTcsImV4cCI6MTY5NjUzNTMxN30.U2ZsyrGk7NbsP-DJfdz9xgWSfect5r2iKQnlEsscAA8"
    )
    val refreshToken: String,

    /**
     * Access Token
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.1.0
     */
    @Schema(
        description = "Access Token",
        example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiJkYTllYjFkYmVmZDQ0OWRkOThlOGNjNzZlNzZkMDgyNSIsInN1YiI6IjE3MDk5ODYwNTg2Nzk5NzU5MzgiLCJpc3MiOiJGYXRXZWIiLCJpYXQiOjE2OTY1MjgxMTcsImV4cCI6MTY5NjUzNTMxN30.U2ZsyrGk7NbsP-DJfdz9xgWSfect5r2iKQnlEsscAA8"
    )
    val accessToken: String,

    /**
     * User ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(
        description = "User ID",
        example = "1709986058679975938"
    )
    @JsonSerialize(using = ToStringSerializer::class)
    val userId: Long?,

    /**
     * Last login time
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see LocalDateTime
     */
    @Schema(
        description = "上次登录时间",
        example = "1900-01-01 00:00:00"
    )
    val lastLoginTime: LocalDateTime?,

    /**
     * Last login IP
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(
        description = "上次登录 IP",
        example = "10.0.0.1"
    )
    val lastLoginIp: String?
)
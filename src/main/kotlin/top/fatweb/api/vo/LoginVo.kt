package top.fatweb.api.vo

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

@Schema(description = "登录返回参数")
data class LoginVo(
    @Schema(
        description = "Token",
        example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiJkYTllYjFkYmVmZDQ0OWRkOThlOGNjNzZlNzZkMDgyNSIsInN1YiI6IjE3MDk5ODYwNTg2Nzk5NzU5MzgiLCJpc3MiOiJGYXRXZWIiLCJpYXQiOjE2OTY1MjgxMTcsImV4cCI6MTY5NjUzNTMxN30.U2ZsyrGk7NbsP-DJfdz9xgWSfect5r2iKQnlEsscAA8"
    ) val token: String,

    @Schema(
        description = "上次登录时间",
        example = "1900-01-01 00:00:00"
    ) val lastLoginTime: LocalDateTime?,

    @Schema(
        description = "上次登录 IP",
        example = "10.0.0.1"
    ) val lastLoginIp: String?
)
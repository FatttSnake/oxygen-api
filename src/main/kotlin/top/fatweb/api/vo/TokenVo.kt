package top.fatweb.api.vo

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Token")
data class TokenVo(
    @Schema(
        description = "Token",
        example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiJkYTllYjFkYmVmZDQ0OWRkOThlOGNjNzZlNzZkMDgyNSIsInN1YiI6IjE3MDk5ODYwNTg2Nzk5NzU5MzgiLCJpc3MiOiJGYXRXZWIiLCJpYXQiOjE2OTY1MjgxMTcsImV4cCI6MTY5NjUzNTMxN30.U2ZsyrGk7NbsP-DJfdz9xgWSfect5r2iKQnlEsscAA8"
    ) val token: String
)
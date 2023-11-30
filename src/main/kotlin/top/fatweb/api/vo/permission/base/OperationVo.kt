package top.fatweb.api.vo.permission.base

import io.swagger.v3.oas.annotations.media.Schema

/**
 * Operation value object
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Schema(description = "操作返回参数")
data class OperationVo(
    val id: Long?,

    @Schema(description = "操作名", example = "Add User")
    val name: String?,

    @Schema(description = "操作编码", example = "system:user:add")
    val code: String?,

    @Schema(description = "功能 ID")
    val funcId: Long?
)

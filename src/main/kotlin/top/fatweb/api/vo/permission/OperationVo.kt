package top.fatweb.api.vo.permission

import io.swagger.v3.oas.annotations.media.Schema

/**
 * Operation value object
 *
 * @author FatttSnake
 * @since 1.0.0
 */
@Schema(description = "功能返回参数")
data class OperationVo(
    val id: Long?,

    @Schema(description = "功能名", example = "Add User")
    val name: String?,

    @Schema(description = "功能编码", example = "system:user:add")
    val code: String?,

    @Schema(description = "页面元素 ID")
    val elementId: Long?
)

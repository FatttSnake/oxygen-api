package top.fatweb.oxygen.api.vo.permission.base

import io.swagger.v3.oas.annotations.media.Schema

/**
 * Operation value object
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Schema(description = "操作返回参数")
data class OperationVo(
    /**
     * ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    val id: Long?,

    /**
     * Name
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "操作名", example = "Add User")
    val name: String?,

    /**
     * Code
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "操作编码", example = "system:user:add")
    val code: String?,

    /**
     * Function ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "功能 ID")
    val funcId: Long?
)

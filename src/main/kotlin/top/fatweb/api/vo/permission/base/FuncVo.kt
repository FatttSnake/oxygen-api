package top.fatweb.api.vo.permission.base

import io.swagger.v3.oas.annotations.media.Schema

/**
 * Function value object
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Schema(description = "功能返回参数")
data class FuncVo(
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
    @Schema(description = "功能名", example = "AddButton")
    val name: String?,

    /**
     * Parent ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "父 ID")
    val parentId: Long?,

    /**
     * Menu ID
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "菜单 ID")
    val menuId: Long?
)

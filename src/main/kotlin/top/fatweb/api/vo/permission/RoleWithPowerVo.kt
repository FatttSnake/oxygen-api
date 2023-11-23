package top.fatweb.api.vo.permission

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

/**
 * Role with power value object
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Schema(description = "角色返回参数")
data class RoleWithPowerVo(
    @JsonSerialize(using = ToStringSerializer::class)
    val id: Long?,

    @Schema(description = "角色名", example = "Role")
    val name: String?,

    @Schema(description = "启用", example = "true")
    val enable: Boolean?,

    @Schema(description = "创建时间", example = "1900-01-01T00:00:00.000Z")
    val createTime: LocalDateTime?,

    @Schema(description = "修改时间", example = "1900-01-01T00:00:00.000Z")
    val updateTime: LocalDateTime?,

    @Schema(description = "模块列表")
    val modules: List<ModuleVo>?,

    @Schema(description = "菜单列表")
    val menus: List<MenuVo>?,

    @Schema(description = "页面元素列表")
    val elements: List<ElementVo>?,

    @Schema(description = "功能列表")
    val operations: List<OperationVo>?
)

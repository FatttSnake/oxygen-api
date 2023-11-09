package top.fatweb.api.controller.permission

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import top.fatweb.api.converter.permission.RoleConverter
import top.fatweb.api.entity.common.ResponseCode
import top.fatweb.api.entity.common.ResponseResult
import top.fatweb.api.param.authentication.RoleAddParam
import top.fatweb.api.param.authentication.RoleGetParam
import top.fatweb.api.service.permission.IRoleService
import top.fatweb.api.vo.PageVo
import top.fatweb.api.vo.permission.RoleVo
import top.fatweb.api.vo.permission.RoleWithPowerVo

/**
 * 角色表 前端控制器
 *
 * @author FatttSnake
 * @since 2023-11-09
 */
@Tag(name = "角色管理", description = "角色管理相关接口")
@RestController
@RequestMapping("/system/role")
class RoleController(
    private val roleService: IRoleService
) {
    @Operation(summary = "获取角色列表")
    @GetMapping
    fun get(roleGetParam: RoleGetParam?): ResponseResult<PageVo<RoleWithPowerVo>> {
        return ResponseResult.success(
            ResponseCode.DATABASE_SELECT_SUCCESS, data = RoleConverter.rolePageToRoleWithPowerPageVo(
                roleService.getPage(roleGetParam)
            )
        )
    }

    @Operation(summary = "添加角色")
    @PostMapping
    fun add(@Valid @RequestBody roleAddParam: RoleAddParam): ResponseResult<RoleVo> {
        return roleService.add(roleAddParam)
            ?.let { ResponseResult.success(ResponseCode.DATABASE_INSERT_SUCCESS, data = RoleConverter.roleToRoleVo(it)) }
            ?: let { ResponseResult.fail(ResponseCode.DATABASE_INSERT_FAILED) }
    }
}
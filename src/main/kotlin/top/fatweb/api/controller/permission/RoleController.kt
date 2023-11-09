package top.fatweb.api.controller.permission

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.fatweb.api.converter.permission.RoleConverter
import top.fatweb.api.entity.common.ResponseCode
import top.fatweb.api.entity.common.ResponseResult
import top.fatweb.api.param.authentication.RoleAddParam
import top.fatweb.api.param.authentication.RoleGetParam
import top.fatweb.api.service.permission.IRoleService
import top.fatweb.api.vo.PageVo
import top.fatweb.api.vo.permission.RoleVo

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
    fun get(roleGetParam: RoleGetParam?): ResponseResult<PageVo<RoleVo>> {
        return ResponseResult.success(
            ResponseCode.DATABASE_SELECT_SUCCESS, data = RoleConverter.rolePageToRolePageVo(
                roleService.getPage(roleGetParam)
            )
        )
    }

    @Operation(summary = "添加角色")
    @PostMapping
    fun add(roleAddParam: RoleAddParam): ResponseResult<RoleVo> {
        return roleService.add(roleAddParam)
            ?.let { ResponseResult.success(ResponseCode.DATABASE_INSERT_SUCCESS, data = it) }
            ?: let { ResponseResult.fail(ResponseCode.DATABASE_INSERT_FAILED) }
    }
}
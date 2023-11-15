package top.fatweb.api.controller.permission

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import top.fatweb.api.entity.common.ResponseCode
import top.fatweb.api.entity.common.ResponseResult
import top.fatweb.api.param.authentication.*
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
    @Operation(summary = "获取角色")
    @GetMapping
    fun get(roleGetParam: RoleGetParam?): ResponseResult<PageVo<RoleWithPowerVo>> {
        return ResponseResult.databaseSuccess(
            data = roleService.getPage(roleGetParam)
        )
    }

    @Operation(summary = "获取单个角色")
    @GetMapping("/{id}")
    fun getOne(@PathVariable id: Long): ResponseResult<RoleWithPowerVo> {
        return ResponseResult.databaseSuccess(
            data = roleService.getOne(id)
        )
    }

    @Operation(summary = "获取角色列表")
    @GetMapping("/list")
    fun list(): ResponseResult<List<RoleVo>> {
        return ResponseResult.databaseSuccess(
            data = roleService.listAll()
        )
    }

    @Operation(summary = "添加角色")
    @PostMapping
    fun add(@Valid @RequestBody roleAddParam: RoleAddParam): ResponseResult<RoleVo> {
        return roleService.add(roleAddParam)?.let {
            ResponseResult.databaseSuccess(
                ResponseCode.DATABASE_INSERT_SUCCESS, data = it
            )
        } ?: let { ResponseResult.databaseFail(ResponseCode.DATABASE_INSERT_FAILED) }
    }

    @Operation(summary = "修改角色")
    @PutMapping
    fun update(@Valid @RequestBody roleUpdateParam: RoleUpdateParam): ResponseResult<RoleVo> {
        return roleService.update(roleUpdateParam)?.let {
            ResponseResult.databaseSuccess(
                ResponseCode.DATABASE_UPDATE_SUCCESS, data = it
            )
        } ?: let { ResponseResult.databaseFail(ResponseCode.DATABASE_UPDATE_FILED) }
    }

    @Operation(summary = "修改角色状态")
    @PatchMapping
    fun changStatus(@Valid @RequestBody roleChangeStatusParam: RoleChangeStatusParam): ResponseResult<Nothing> {
        return if (roleService.changeStatus(roleChangeStatusParam)) {
            ResponseResult.databaseSuccess(ResponseCode.DATABASE_UPDATE_SUCCESS)
        } else {
            ResponseResult.databaseFail(ResponseCode.DATABASE_UPDATE_FILED)
        }
    }

    @Operation(summary = "删除角色")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseResult<Nothing> {
        roleService.deleteOne(id)
        return ResponseResult.databaseSuccess(ResponseCode.DATABASE_DELETE_SUCCESS)
    }

    @Operation(summary = "批量删除角色")
    @DeleteMapping
    fun deleteList(@Valid @RequestBody roleDeleteParam: RoleDeleteParam): ResponseResult<Nothing> {
        roleService.delete(roleDeleteParam)
        return ResponseResult.databaseSuccess(ResponseCode.DATABASE_DELETE_SUCCESS)
    }
}
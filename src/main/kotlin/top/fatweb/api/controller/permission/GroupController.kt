package top.fatweb.api.controller.permission

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import top.fatweb.api.entity.common.ResponseCode
import top.fatweb.api.entity.common.ResponseResult
import top.fatweb.api.param.authentication.*
import top.fatweb.api.service.permission.IGroupService
import top.fatweb.api.vo.PageVo
import top.fatweb.api.vo.permission.GroupVo
import top.fatweb.api.vo.permission.GroupWithRoleVo

/**
 * Group controller
 *
 * @author FatttSnake
 * @since 1.0.0
 */
@Tag(name = "用户组管理", description = "用户组管理相关接口")
@RestController
@RequestMapping("/system/group")
class GroupController(
    val groupService: IGroupService
) {
    @Operation(summary = "获取用户组")
    @GetMapping
    fun get(@Valid groupGetParam: GroupGetParam?): ResponseResult<PageVo<GroupWithRoleVo>> {
        return ResponseResult.databaseSuccess(
            data = groupService.getPage(groupGetParam)
        )
    }

    @Operation(summary = "获取单个用户组")
    @GetMapping("/{id}")
    fun getOne(@PathVariable id: Long): ResponseResult<GroupWithRoleVo> {
        return ResponseResult.databaseSuccess(
            data = groupService.getOne(id)
        )
    }

    @Operation(summary = "获取用户组列表")
    @GetMapping("/list")
    fun list(): ResponseResult<List<GroupVo>> {
        return ResponseResult.databaseSuccess(
            data = groupService.listAll()
        )
    }

    @Operation(summary = "添加用户组")
    @PostMapping
    fun add(@Valid @RequestBody groupAddParam: GroupAddParam): ResponseResult<GroupVo> {
        return groupService.add(groupAddParam)?.let {
            ResponseResult.databaseSuccess(
                ResponseCode.DATABASE_INSERT_SUCCESS, data = it
            )
        } ?: let { ResponseResult.databaseFail(ResponseCode.DATABASE_INSERT_FAILED) }
    }

    @Operation(summary = "修改用户组")
    @PutMapping
    fun update(@Valid @RequestBody groupUpdateParam: GroupUpdateParam): ResponseResult<GroupVo> {
        return groupService.update(groupUpdateParam)?.let {
            ResponseResult.databaseSuccess(
                ResponseCode.DATABASE_UPDATE_SUCCESS, data = it
            )
        } ?: let { ResponseResult.databaseFail(ResponseCode.DATABASE_UPDATE_FILED) }
    }

    @Operation(summary = "修改用户组状态")
    @PatchMapping
    fun changStatus(@Valid @RequestBody groupChangeStatusParam: GroupChangeStatusParam): ResponseResult<Nothing> {
        return if (groupService.changeStatus(groupChangeStatusParam)) {
            ResponseResult.databaseSuccess(ResponseCode.DATABASE_UPDATE_SUCCESS)
        } else {
            ResponseResult.databaseFail(ResponseCode.DATABASE_UPDATE_FILED)
        }
    }

    @Operation(summary = "删除角色")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseResult<Nothing> {
        groupService.deleteOne(id)
        return ResponseResult.databaseSuccess(ResponseCode.DATABASE_DELETE_SUCCESS)
    }

    @Operation(summary = "批量删除角色")
    @DeleteMapping
    fun deleteList(@Valid @RequestBody groupDeleteParam: GroupDeleteParam): ResponseResult<Nothing> {
        groupService.delete(groupDeleteParam)
        return ResponseResult.databaseSuccess(ResponseCode.DATABASE_DELETE_SUCCESS)
    }
}
package top.fatweb.api.controller.permission

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.fatweb.api.converter.permission.GroupConverter
import top.fatweb.api.entity.common.ResponseResult
import top.fatweb.api.param.authentication.GroupGetParam
import top.fatweb.api.service.permission.IGroupService
import top.fatweb.api.vo.PageVo
import top.fatweb.api.vo.permission.GroupVo

/**
 * <p>
 * 用户组表 前端控制器
 * </p>
 *
 * @author FatttSnake
 * @since 2023-11-09
 */
@Tag(name = "用户组管理", description = "用户组管理相关接口")
@RestController
@RequestMapping("/system/group")
class GroupController(
    val groupService: IGroupService
) {
    @Operation(summary = "获取用户组列表")
    @GetMapping
    fun get(@Valid groupGetParam: GroupGetParam?): ResponseResult<PageVo<GroupVo>> {
        return ResponseResult.databaseSuccess(
            data = GroupConverter.groupPageToGroupPageVo(
                groupService.getPage(groupGetParam)
            )
        )
    }
}
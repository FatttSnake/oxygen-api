package top.fatweb.api.controller.api.v1

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.fatweb.api.entity.common.ResponseResult
import top.fatweb.api.service.api.v1.IAvatarService
import top.fatweb.api.vo.api.v1.avatar.DefaultBase64Vo

/**
 * Avatar controller
 *
 * @author FatttSnake
 * @since 1.0.0
 */
@Tag(name = "随机头像", description = "随机头像相关接口")
@RestController
@RequestMapping("/api/{apiVersion}/avatar")
class AvatarController(
    private val avatarService: IAvatarService
) {
    @Operation(summary = "获取默认随机头像")
    @GetMapping
    fun getDefault(@PathVariable apiVersion: String): ResponseResult<DefaultBase64Vo> {
        return ResponseResult.success(data = avatarService.getDefault())
    }
}
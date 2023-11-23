package top.fatweb.api.controller.api.v1

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.fatweb.api.entity.common.ResponseResult
import top.fatweb.api.param.api.v1.avatar.AvatarBaseParam
import top.fatweb.api.param.api.v1.avatar.AvatarEightBitParam
import top.fatweb.api.param.api.v1.avatar.AvatarGitHubParam
import top.fatweb.api.service.api.v1.IAvatarService
import top.fatweb.api.vo.api.v1.avatar.DefaultBase64Vo

/**
 * Avatar controller
 *
 * @author FatttSnake, fatttsnake@gmail.com
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

    @Operation(summary = "三角形头像")
    @GetMapping("/triangle", produces = [MediaType.IMAGE_PNG_VALUE])
    fun triangle(@PathVariable apiVersion: String, @Valid avatarBaseParam: AvatarBaseParam?): ByteArray {
        return avatarService.triangle(avatarBaseParam)
    }

    @Operation(summary = "正方形头像")
    @GetMapping("/square", produces = [MediaType.IMAGE_PNG_VALUE])
    fun square(@PathVariable apiVersion: String, @Valid avatarBaseParam: AvatarBaseParam?): ByteArray {
        return avatarService.square(avatarBaseParam)
    }

    @Operation(summary = "Identicon 头像")
    @GetMapping("/identicon", produces = [MediaType.IMAGE_PNG_VALUE])
    fun identicon(@PathVariable apiVersion: String, @Valid avatarBaseParam: AvatarBaseParam?): ByteArray {
        return avatarService.identicon(avatarBaseParam)
    }

    @Operation(summary = "GitHub 头像")
    @GetMapping("/github", produces = [MediaType.IMAGE_PNG_VALUE])
    fun github(@PathVariable apiVersion: String, @Valid avatarGitHubParam: AvatarGitHubParam?): ByteArray {
        return avatarService.github(avatarGitHubParam)
    }

    @Operation(summary = "8 Bit 头像")
    @GetMapping("/8bit", produces = [MediaType.IMAGE_PNG_VALUE])
    fun eightBit(@PathVariable apiVersion: String, @Valid avatarEightBitParam: AvatarEightBitParam): ByteArray {
        return avatarService.eightBit(avatarEightBitParam)
    }
}
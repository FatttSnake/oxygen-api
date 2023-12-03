package top.fatweb.api.controller.api.v1

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.fatweb.api.entity.common.ResponseCode
import top.fatweb.api.entity.common.ResponseResult
import top.fatweb.api.param.api.v1.avatar.AvatarBaseParam
import top.fatweb.api.param.api.v1.avatar.AvatarGitHubParam
import top.fatweb.api.service.api.v1.IAvatarService
import top.fatweb.api.vo.api.v1.avatar.AvatarBase64Vo

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
    /**
     * Get random avatar
     *
     * @param apiVersion Api version
     * @param avatarBaseParam Avatar base parameters
     * @return Avatar byte array
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see AvatarBaseParam
     * @see ByteArray
     */
    @Operation(summary = "获取随机头像")
    @GetMapping(produces = [MediaType.IMAGE_PNG_VALUE])
    fun getRandom(@PathVariable apiVersion: String, @Valid avatarBaseParam: AvatarBaseParam?): ByteArray =
        when ((1..4).random()) {
            1 -> avatarService.triangle(avatarBaseParam)
            2 -> avatarService.square(avatarBaseParam)
            3 -> avatarService.identicon(avatarBaseParam)
            else -> avatarService.github(AvatarGitHubParam().apply {
                seed = avatarBaseParam?.seed
                size = avatarBaseParam?.size
                margin = avatarBaseParam?.margin
                padding = avatarBaseParam?.padding
                colors = avatarBaseParam?.colors
                background = avatarBaseParam?.background
            })
        }

    /**
     * Get random avatar as base64
     *
     * @param apiVersion Api version
     * @param avatarBaseParam Avatar base parameters
     * @return Response object includes avatar base64 string
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see AvatarBaseParam
     * @see ResponseResult
     * @see AvatarBase64Vo
     */
    @Operation(summary = "获取随机头像 Base64")
    @GetMapping("base64")
    fun getRandomBase64(
        @PathVariable apiVersion: String,
        @Valid avatarBaseParam: AvatarBaseParam?
    ): ResponseResult<AvatarBase64Vo> =
        ResponseResult.success(
            ResponseCode.API_AVATAR_SUCCESS, data = when ((1..4).random()) {
                1 -> avatarService.triangleBase64(avatarBaseParam)
                2 -> avatarService.squareBase64(avatarBaseParam)
                3 -> avatarService.identiconBase64(avatarBaseParam)
                else -> avatarService.githubBase64(AvatarGitHubParam().apply {
                    seed = avatarBaseParam?.seed
                    size = avatarBaseParam?.size
                    margin = avatarBaseParam?.margin
                    padding = avatarBaseParam?.padding
                    colors = avatarBaseParam?.colors
                    background = avatarBaseParam?.background
                })
            }
        )

    /**
     * Get triangle avatar
     *
     * @param apiVersion Api version
     * @param avatarBaseParam Avatar base parameters
     * @return Avatar byte array
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see AvatarBaseParam
     * @see ByteArray
     */
    @Operation(summary = "三角形头像")
    @GetMapping("/triangle", produces = [MediaType.IMAGE_PNG_VALUE])
    fun triangle(@PathVariable apiVersion: String, @Valid avatarBaseParam: AvatarBaseParam?): ByteArray =
        avatarService.triangle(avatarBaseParam)

    /**
     * Get triangle avatar as base64
     *
     * @param apiVersion Api version
     * @param avatarBaseParam Avatar base parameters
     * @return Response object includes avatar base64 string
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see AvatarBaseParam
     * @see ResponseResult
     * @see AvatarBase64Vo
     */
    @Operation(summary = "三角形头像 Base64")
    @GetMapping("/triangle/base64")
    fun triangleBase64(
        @PathVariable apiVersion: String,
        @Valid avatarBaseParam: AvatarBaseParam?
    ): ResponseResult<AvatarBase64Vo> =
        ResponseResult.success(
            ResponseCode.API_AVATAR_SUCCESS,
            data = avatarService.triangleBase64(avatarBaseParam)
        )

    /**
     * Get square avatar
     *
     * @param apiVersion Api version
     * @param avatarBaseParam Avatar base parameters
     * @return Avatar byte array
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see AvatarBaseParam
     * @see ByteArray
     */
    @Operation(summary = "正方形头像")
    @GetMapping("/square", produces = [MediaType.IMAGE_PNG_VALUE])
    fun square(@PathVariable apiVersion: String, @Valid avatarBaseParam: AvatarBaseParam?): ByteArray =
        avatarService.square(avatarBaseParam)

    /**
     * Get square avatar as base64
     *
     * @param apiVersion Api version
     * @param avatarBaseParam Avatar base parameters
     * @return Response object includes avatar base64 string
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see AvatarBaseParam
     * @see ResponseResult
     * @see AvatarBase64Vo
     */
    @Operation(summary = "正方形头像 Base64")
    @GetMapping("/square/base64")
    fun squareBase64(
        @PathVariable apiVersion: String,
        @Valid avatarBaseParam: AvatarBaseParam?
    ): ResponseResult<AvatarBase64Vo> =
        ResponseResult.success(
            ResponseCode.API_AVATAR_SUCCESS,
            data = avatarService.squareBase64(avatarBaseParam)
        )

    /**
     * Get identicon avatar
     *
     * @param apiVersion Api version
     * @param avatarBaseParam Avatar base parameters
     * @return Avatar byte array
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see AvatarBaseParam
     * @see ByteArray
     */
    @Operation(summary = "Identicon 头像")
    @GetMapping("/identicon", produces = [MediaType.IMAGE_PNG_VALUE])
    fun identicon(@PathVariable apiVersion: String, @Valid avatarBaseParam: AvatarBaseParam?): ByteArray =
        avatarService.identicon(avatarBaseParam)

    /**
     * Get identicon avatar as base64
     *
     * @param apiVersion Api version
     * @param avatarBaseParam Avatar base parameters
     * @return Response object includes avatar base64 string
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see AvatarBaseParam
     * @see ResponseResult
     * @see AvatarBase64Vo
     */
    @Operation(summary = "Identicon 头像 Base64")
    @GetMapping("/identicon/base64")
    fun identiconBase64(
        @PathVariable apiVersion: String,
        @Valid avatarBaseParam: AvatarBaseParam?
    ): ResponseResult<AvatarBase64Vo> =
        ResponseResult.success(
            ResponseCode.API_AVATAR_SUCCESS,
            data = avatarService.identiconBase64(avatarBaseParam)
        )

    /**
     * Get GitHub avatar
     *
     * @param apiVersion Api version
     * @param avatarGitHubParam Avatar base parameters
     * @return Avatar byte array
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see AvatarGitHubParam
     * @see ByteArray
     */
    @Operation(summary = "GitHub 头像")
    @GetMapping("/github", produces = [MediaType.IMAGE_PNG_VALUE])
    fun github(@PathVariable apiVersion: String, @Valid avatarGitHubParam: AvatarGitHubParam?): ByteArray =
        avatarService.github(avatarGitHubParam)

    /**
     * Get GitHub avatar as base64
     *
     * @param apiVersion Api version
     * @param avatarGitHubParam Avatar base parameters
     * @return Response object includes avatar base64 string
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see AvatarGitHubParam
     * @see ResponseResult
     * @see AvatarBase64Vo
     */
    @Operation(summary = "GitHub 头像 Base64")
    @GetMapping("/github/base64")
    fun githubBase64(
        @PathVariable apiVersion: String,
        @Valid avatarGitHubParam: AvatarGitHubParam?
    ): ResponseResult<AvatarBase64Vo> =
        ResponseResult.success(
            ResponseCode.API_AVATAR_SUCCESS,
            data = avatarService.githubBase64(avatarGitHubParam)
        )
}
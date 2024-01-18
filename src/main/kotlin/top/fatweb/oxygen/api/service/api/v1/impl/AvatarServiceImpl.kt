package top.fatweb.oxygen.api.service.api.v1.impl

import org.springframework.stereotype.Service
import top.fatweb.avatargenerator.GitHubAvatar
import top.fatweb.avatargenerator.IdenticonAvatar
import top.fatweb.avatargenerator.SquareAvatar
import top.fatweb.avatargenerator.TriangleAvatar
import top.fatweb.avatargenerator.layer.background.ColorPaintBackgroundLayer
import top.fatweb.oxygen.api.param.api.v1.avatar.AvatarBaseParam
import top.fatweb.oxygen.api.param.api.v1.avatar.AvatarGitHubParam
import top.fatweb.oxygen.api.service.api.v1.IAvatarService
import top.fatweb.oxygen.api.util.NumberUtil
import top.fatweb.oxygen.api.vo.api.v1.avatar.AvatarBase64Vo
import java.awt.Color
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

/**
 * Avatar service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see IAvatarService
 */
@OptIn(ExperimentalEncodingApi::class)
@Service
class AvatarServiceImpl : IAvatarService {
    override fun random(avatarBaseParam: AvatarBaseParam?): ByteArray =
        when ((1..4).random()) {
            1 -> triangle(avatarBaseParam)
            2 -> square(avatarBaseParam)
            3 -> identicon(avatarBaseParam)
            else -> github(AvatarGitHubParam().apply {
                seed = avatarBaseParam?.seed
                size = avatarBaseParam?.size
                margin = avatarBaseParam?.margin
                padding = avatarBaseParam?.padding
                colors = avatarBaseParam?.colors
                background = avatarBaseParam?.background
            })
        }

    override fun randomBase64(avatarBaseParam: AvatarBaseParam?): AvatarBase64Vo = when ((1..4).random()) {
        1 -> triangleBase64(avatarBaseParam)
        2 -> squareBase64(avatarBaseParam)
        3 -> identiconBase64(avatarBaseParam)
        else -> githubBase64(AvatarGitHubParam().apply {
            seed = avatarBaseParam?.seed
            size = avatarBaseParam?.size
            margin = avatarBaseParam?.margin
            padding = avatarBaseParam?.padding
            colors = avatarBaseParam?.colors
            background = avatarBaseParam?.background
        })
    }

    override fun triangle(avatarBaseParam: AvatarBaseParam?): ByteArray {
        val avatar = (
            if (avatarBaseParam == null || avatarBaseParam.colors.isNullOrEmpty())
                TriangleAvatar.newAvatarBuilder()
            else TriangleAvatar.newAvatarBuilder(
                *avatarBaseParam.colors!!.map(this::decodeColor).toTypedArray()
            )
            ).apply {
                avatarBaseParam?.size?.let(this::size)
                avatarBaseParam?.margin?.let(this::margin)
                avatarBaseParam?.padding?.let(this::padding)
                avatarBaseParam?.background?.let { layers(ColorPaintBackgroundLayer(decodeColor(it))) }
            }.build()

        return avatar.createAsPngBytes(avatarBaseParam?.seed ?: NumberUtil.getRandomLong())
    }

    override fun triangleBase64(avatarBaseParam: AvatarBaseParam?) =
        AvatarBase64Vo(Base64.encode(triangle(avatarBaseParam)))

    override fun square(avatarBaseParam: AvatarBaseParam?): ByteArray {
        val avatar = (
            if (avatarBaseParam == null || avatarBaseParam.colors.isNullOrEmpty())
                SquareAvatar.newAvatarBuilder()
            else SquareAvatar.newAvatarBuilder(
                *avatarBaseParam.colors!!.map(this::decodeColor).toTypedArray()
            )
            ).apply {
                avatarBaseParam?.size?.let(this::size)
                avatarBaseParam?.margin?.let(this::margin)
                avatarBaseParam?.padding?.let(this::padding)
                avatarBaseParam?.background?.let { layers(ColorPaintBackgroundLayer(decodeColor(it))) }
            }.build()

        return avatar.createAsPngBytes(avatarBaseParam?.seed ?: NumberUtil.getRandomLong())
    }

    override fun squareBase64(avatarBaseParam: AvatarBaseParam?) =
        AvatarBase64Vo(Base64.encode(square(avatarBaseParam)))

    override fun identicon(avatarBaseParam: AvatarBaseParam?): ByteArray {
        val avatar = IdenticonAvatar.newAvatarBuilder().apply {
            avatarBaseParam?.size?.let(this::size)
            avatarBaseParam?.margin?.let(this::margin)
            avatarBaseParam?.padding?.let(this::padding)
            if (avatarBaseParam != null && !avatarBaseParam.colors.isNullOrEmpty()) {
                color(decodeColor(avatarBaseParam.colors!!.random()))
            }
            avatarBaseParam?.background?.let { layers(ColorPaintBackgroundLayer(decodeColor(it))) }
        }.build()

        return avatar.createAsPngBytes(avatarBaseParam?.seed ?: NumberUtil.getRandomLong())
    }

    override fun identiconBase64(avatarBaseParam: AvatarBaseParam?) =
        AvatarBase64Vo(Base64.encode(identicon(avatarBaseParam)))

    override fun github(avatarGitHubParam: AvatarGitHubParam?): ByteArray {
        val avatar = (avatarGitHubParam?.let { GitHubAvatar.newAvatarBuilder(it.elementSize, it.precision) }
            ?: let { GitHubAvatar.newAvatarBuilder(400, 5) }).apply {
            avatarGitHubParam?.size?.let(this::size)
            avatarGitHubParam?.margin?.let(this::margin)
            avatarGitHubParam?.padding?.let(this::padding)
            if (avatarGitHubParam != null && !avatarGitHubParam.colors.isNullOrEmpty()) {
                color(decodeColor(avatarGitHubParam.colors!!.random()))
            }
            avatarGitHubParam?.background?.let { layers(ColorPaintBackgroundLayer(decodeColor(it))) }
        }.build()

        return avatar.createAsPngBytes(avatarGitHubParam?.seed ?: NumberUtil.getRandomLong())
    }

    override fun githubBase64(avatarGitHubParam: AvatarGitHubParam?) =
        AvatarBase64Vo(Base64.encode(github(avatarGitHubParam)))

    fun decodeColor(nm: String): Color {
        return if (Regex("^#[0-9a-fA-F]{6}$").matches(nm)) {
            Color.decode(nm)
        } else if (Regex("^#[0-9a-fA-F]{8}$").matches(nm)) {
            val intVal = Integer.decode(nm.substring(1..6).prependIndent("#"))
            val alpha = Integer.decode(nm.substring(7).prependIndent("#"))
            Color(intVal shr 16 and 0xFF, intVal shr 8 and 0XFF, intVal and 0xFF, alpha and 0xFF)
        } else {
            Color.WHITE
        }
    }

}
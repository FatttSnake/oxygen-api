package top.fatweb.api.service.api.v1.impl

import org.springframework.stereotype.Service
import top.fatweb.api.param.api.v1.avatar.AvatarBaseParam
import top.fatweb.api.param.api.v1.avatar.AvatarGitHubParam
import top.fatweb.api.service.api.v1.IAvatarService
import top.fatweb.api.util.NumberUtil
import top.fatweb.api.vo.api.v1.avatar.AvatarBase64Vo
import top.fatweb.avatargenerator.GitHubAvatar
import top.fatweb.avatargenerator.IdenticonAvatar
import top.fatweb.avatargenerator.SquareAvatar
import top.fatweb.avatargenerator.TriangleAvatar
import top.fatweb.avatargenerator.layer.background.ColorPaintBackgroundLayer
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

    override fun triangle(avatarBaseParam: AvatarBaseParam?): ByteArray {
        val avatar = (
            if (avatarBaseParam == null || avatarBaseParam.colors.isNullOrEmpty())
                TriangleAvatar.newAvatarBuilder()
            else TriangleAvatar.newAvatarBuilder(
                *avatarBaseParam.colors!!.map { decodeColor(it) }.toTypedArray()
            )
            ).apply {
                avatarBaseParam?.size?.let { size(it, it) }
                avatarBaseParam?.margin?.let { margin(it) }
                avatarBaseParam?.padding?.let { padding(it) }
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
                *avatarBaseParam.colors!!.map { decodeColor(it) }.toTypedArray()
            )
            ).apply {
                avatarBaseParam?.size?.let { size(it, it) }
                avatarBaseParam?.margin?.let { margin(it) }
                avatarBaseParam?.padding?.let { padding(it) }
                avatarBaseParam?.background?.let { layers(ColorPaintBackgroundLayer(decodeColor(it))) }
            }.build()

        return avatar.createAsPngBytes(avatarBaseParam?.seed ?: NumberUtil.getRandomLong())
    }

    override fun squareBase64(avatarBaseParam: AvatarBaseParam?) =
        AvatarBase64Vo(Base64.encode(square(avatarBaseParam)))

    override fun identicon(avatarBaseParam: AvatarBaseParam?): ByteArray {
        val avatar = IdenticonAvatar.newAvatarBuilder().apply {
            avatarBaseParam?.size?.let { size(it, it) }
            avatarBaseParam?.margin?.let { margin(it) }
            avatarBaseParam?.padding?.let { padding(it) }
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
            avatarGitHubParam?.size?.let { size(it, it) }
            avatarGitHubParam?.margin?.let { margin(it) }
            avatarGitHubParam?.padding?.let { padding(it) }
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
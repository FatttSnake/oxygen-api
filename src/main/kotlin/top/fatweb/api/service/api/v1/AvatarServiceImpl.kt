package top.fatweb.api.service.api.v1

import com.talanlabs.avatargenerator.GitHubAvatar
import com.talanlabs.avatargenerator.IdenticonAvatar
import com.talanlabs.avatargenerator.SquareAvatar
import com.talanlabs.avatargenerator.TriangleAvatar
import com.talanlabs.avatargenerator.eightbit.EightBitAvatar
import com.talanlabs.avatargenerator.layers.backgrounds.ColorPaintBackgroundLayer
import org.springframework.stereotype.Service
import top.fatweb.api.param.api.v1.avatar.AvatarBaseParam
import top.fatweb.api.param.api.v1.avatar.AvatarEightBitParam
import top.fatweb.api.param.api.v1.avatar.AvatarGitHubParam
import top.fatweb.api.util.NumberUtil
import top.fatweb.api.vo.api.v1.avatar.DefaultBase64Vo
import java.awt.Color
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@Service
class AvatarServiceImpl : IAvatarService {
    @OptIn(ExperimentalEncodingApi::class)
    override fun getDefault(): DefaultBase64Vo {
        val avatar = GitHubAvatar.newAvatarBuilder(396, 5).build()
        val bytes = avatar.createAsPngBytes(1232132134543L)
        return DefaultBase64Vo(Base64.encode(bytes))
    }

    override fun triangle(avatarBaseParam: AvatarBaseParam?): ByteArray {
        val avatar = (
            if (avatarBaseParam == null || avatarBaseParam.colors.isNullOrEmpty())
                TriangleAvatar.newAvatarBuilder()
            else TriangleAvatar.newAvatarBuilder(
                *avatarBaseParam.colors!!.map { decodeColor(it) }.toTypedArray())
            ).apply {
                avatarBaseParam?.size?.let { size(it, it) }
                avatarBaseParam?.margin?.let { margin(it) }
                avatarBaseParam?.padding?.let { padding(it) }
                avatarBaseParam?.background?.let { layers(ColorPaintBackgroundLayer(decodeColor(it))) }
            }.build()

        return avatar.createAsPngBytes(avatarBaseParam?.seed ?: NumberUtil.getRandomLong())
    }

    override fun square(avatarBaseParam: AvatarBaseParam?): ByteArray {
        val avatar = (
            if (avatarBaseParam == null || avatarBaseParam.colors.isNullOrEmpty())
                SquareAvatar.newAvatarBuilder()
            else SquareAvatar.newAvatarBuilder(
                *avatarBaseParam.colors!!.map { decodeColor(it) }.toTypedArray())
            ).apply {
                avatarBaseParam?.size?.let { size(it, it) }
                avatarBaseParam?.margin?.let { margin(it) }
                avatarBaseParam?.padding?.let { padding(it) }
                avatarBaseParam?.background?.let { layers(ColorPaintBackgroundLayer(decodeColor(it))) }
            }.build()

        return avatar.createAsPngBytes(avatarBaseParam?.seed ?: NumberUtil.getRandomLong())
    }

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

    override fun github(avatarGitHubParam: AvatarGitHubParam?): ByteArray {
        val avatar = (avatarGitHubParam?.let { GitHubAvatar.newAvatarBuilder(it.elementSize, it.precision) }
            ?: let { GitHubAvatar.newAvatarBuilder() }).apply {
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

    override fun eightBit(avatarEightBitParam: AvatarEightBitParam?): ByteArray {
        val avatar = if (avatarEightBitParam?.gender?.equals("female") ?: false) {
            EightBitAvatar.newFemaleAvatarBuilder().apply {
                avatarEightBitParam?.size?.let { size(it, it) }
                avatarEightBitParam?.margin?.let { margin(it) }
                avatarEightBitParam?.padding?.let { padding(it) }
                if (avatarEightBitParam != null && !avatarEightBitParam.colors.isNullOrEmpty()) {
                    color(decodeColor(avatarEightBitParam.colors!!.random()))
                }
                avatarEightBitParam?.background?.let { layers(ColorPaintBackgroundLayer(decodeColor(it))) }
            }.build()
        } else {
            EightBitAvatar.newMaleAvatarBuilder().apply {
                avatarEightBitParam?.size?.let { size(it, it) }
                avatarEightBitParam?.margin?.let { margin(it) }
                avatarEightBitParam?.padding?.let { padding(it) }
                if (avatarEightBitParam != null && !avatarEightBitParam.colors.isNullOrEmpty()) {
                    color(decodeColor(avatarEightBitParam.colors!!.random()))
                }
                avatarEightBitParam?.background?.let { layers(ColorPaintBackgroundLayer(decodeColor(it))) }
            }.build()
        }

        return avatar.createAsPngBytes(avatarEightBitParam?.seed ?: NumberUtil.getRandomLong())
    }

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
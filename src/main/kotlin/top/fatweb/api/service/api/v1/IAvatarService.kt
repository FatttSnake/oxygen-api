package top.fatweb.api.service.api.v1

import top.fatweb.api.param.api.v1.avatar.AvatarBaseParam
import top.fatweb.api.param.api.v1.avatar.AvatarEightBitParam
import top.fatweb.api.param.api.v1.avatar.AvatarGitHubParam
import top.fatweb.api.vo.api.v1.avatar.DefaultBase64Vo

interface IAvatarService {
    fun getDefault(): DefaultBase64Vo

    fun triangle(avatarBaseParam: AvatarBaseParam?): ByteArray

    fun square(avatarBaseParam: AvatarBaseParam?): ByteArray

    fun identicon(avatarBaseParam: AvatarBaseParam?): ByteArray

    fun github(avatarGitHubParam: AvatarGitHubParam?): ByteArray

//    fun eightBit(avatarEightBitParam: AvatarEightBitParam?): ByteArray

}
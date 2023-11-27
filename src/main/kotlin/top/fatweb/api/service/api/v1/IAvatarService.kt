package top.fatweb.api.service.api.v1

import top.fatweb.api.param.api.v1.avatar.AvatarBaseParam
import top.fatweb.api.param.api.v1.avatar.AvatarGitHubParam
import top.fatweb.api.vo.api.v1.avatar.AvatarBase64Vo

interface IAvatarService {
    fun triangle(avatarBaseParam: AvatarBaseParam?): ByteArray

    fun triangleBase64(avatarBaseParam: AvatarBaseParam?): AvatarBase64Vo

    fun square(avatarBaseParam: AvatarBaseParam?): ByteArray

    fun squareBase64(avatarBaseParam: AvatarBaseParam?): AvatarBase64Vo

    fun identicon(avatarBaseParam: AvatarBaseParam?): ByteArray

    fun identiconBase64(avatarBaseParam: AvatarBaseParam?): AvatarBase64Vo

    fun github(avatarGitHubParam: AvatarGitHubParam?): ByteArray

    fun githubBase64(avatarGitHubParam: AvatarGitHubParam?): AvatarBase64Vo

//    fun eightBit(avatarEightBitParam: AvatarEightBitParam?): ByteArray

}
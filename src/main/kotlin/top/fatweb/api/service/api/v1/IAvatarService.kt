package top.fatweb.api.service.api.v1

import top.fatweb.api.vo.api.v1.avatar.DefaultBase64Vo

interface IAvatarService {
    fun getDefault(): DefaultBase64Vo
}
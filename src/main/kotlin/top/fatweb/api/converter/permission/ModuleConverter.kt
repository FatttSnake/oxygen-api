package top.fatweb.api.converter.permission

import top.fatweb.api.entity.permission.Module
import top.fatweb.api.vo.permission.ModuleVo

object ModuleConverter {
    fun moduleToModuleVo(module: Module) = ModuleVo(
        id = module.id,
        name = module.name
    )
}
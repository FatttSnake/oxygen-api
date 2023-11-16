package top.fatweb.api.converter.permission

import top.fatweb.api.entity.permission.Module
import top.fatweb.api.vo.permission.ModuleVo

/**
 * Module converter
 *
 * @author FatttSnake
 * @since 1.0.0
 */
object ModuleConverter {
    fun moduleToModuleVo(module: Module) = ModuleVo(
        id = module.id,
        name = module.name
    )
}
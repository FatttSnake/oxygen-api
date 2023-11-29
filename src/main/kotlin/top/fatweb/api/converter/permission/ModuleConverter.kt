package top.fatweb.api.converter.permission

import top.fatweb.api.entity.permission.Module
import top.fatweb.api.vo.permission.base.ModuleVo

/**
 * Module converter
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
object ModuleConverter {
    fun moduleToModuleVo(module: Module) = ModuleVo(
        id = module.id,
        name = module.name
    )
}
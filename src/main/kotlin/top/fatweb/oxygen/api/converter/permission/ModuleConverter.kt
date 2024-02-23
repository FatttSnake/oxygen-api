package top.fatweb.oxygen.api.converter.permission

import top.fatweb.oxygen.api.entity.permission.Module
import top.fatweb.oxygen.api.vo.permission.base.ModuleVo

/**
 * Module converter
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
object ModuleConverter {
    /**
     * Convert Module object into ModuleVo object
     *
     * @param module Module object
     * @return ModuleVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see Module
     * @see ModuleVo
     */
    fun moduleToModuleVo(module: Module) = ModuleVo(
        id = module.id,
        name = module.name
    )
}
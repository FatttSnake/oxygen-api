package top.fatweb.oxygen.api.converter.permission

import top.fatweb.oxygen.api.entity.permission.Module
import top.fatweb.oxygen.api.vo.permission.base.ModuleVo

/**
 * Convert to ModuleVo object
 *
 * @return ModuleVo object
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.1.0
 * @see Module
 * @see ModuleVo
 */
fun Module.toVo() = ModuleVo(
    id = this.id,
    name = this.name
)

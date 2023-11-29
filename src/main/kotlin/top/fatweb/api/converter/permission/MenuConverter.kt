package top.fatweb.api.converter.permission

import top.fatweb.api.entity.permission.Menu
import top.fatweb.api.vo.permission.base.MenuVo

/**
 * Menu converter
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
object MenuConverter {
    fun menuToMenuVo(menu: Menu) = MenuVo(
        id = menu.id,
        name = menu.name,
        url = menu.url,
        parentId = menu.parentId,
        moduleId = menu.moduleId
    )
}
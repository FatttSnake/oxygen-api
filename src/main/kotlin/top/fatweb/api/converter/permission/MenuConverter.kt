package top.fatweb.api.converter.permission

import top.fatweb.api.entity.permission.Menu
import top.fatweb.api.vo.permission.MenuVo

object MenuConverter {
    fun menuToMenuVo(menu: Menu) = MenuVo(
        id = menu.id,
        name = menu.name,
        url = menu.url,
        powerId = menu.powerId,
        parentId = menu.parentId,
        moduleId = menu.moduleId
    )
}
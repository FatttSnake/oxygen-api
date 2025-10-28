package top.fatweb.oxygen.api.converter.permission

import top.fatweb.oxygen.api.entity.permission.Menu
import top.fatweb.oxygen.api.vo.permission.base.MenuVo

/**
 * Convert to MenuVo object
 *
 * @return MenuVo object
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.1.0
 * @see Menu
 * @see MenuVo
 */
fun Menu.toVo() = MenuVo(
    id = this.id,
    name = this.name,
    url = this.url,
    parentId = this.parentId,
    moduleId = this.moduleId
)

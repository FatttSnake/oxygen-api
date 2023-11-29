package top.fatweb.api.converter.permission

import top.fatweb.api.entity.permission.Element
import top.fatweb.api.vo.permission.base.ElementVo

/**
 * Element converter
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
object ElementConverter {
    fun elementToElementVo(element: Element) = ElementVo(
        id = element.id,
        name = element.name,
        parentId = element.parentId,
        menuId = element.menuId
    )
}
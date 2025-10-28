package top.fatweb.oxygen.api.converter.permission

import top.fatweb.oxygen.api.entity.permission.Func
import top.fatweb.oxygen.api.vo.permission.base.FuncVo

/**
 * Convert to FuncVo object
 *
 * @return FuncVo object
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.1.0
 * @see Func
 * @see FuncVo
 */
fun Func.toVo() = FuncVo(
    id = this.id,
    name = this.name,
    parentId = this.parentId,
    menuId = this.menuId
)

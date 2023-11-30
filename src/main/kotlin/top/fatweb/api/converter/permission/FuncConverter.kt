package top.fatweb.api.converter.permission

import top.fatweb.api.entity.permission.Func
import top.fatweb.api.vo.permission.base.FuncVo

/**
 * Function converter
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
object FuncConverter {
    fun funcToFuncVo(func: Func) = FuncVo(
        id = func.id,
        name = func.name,
        parentId = func.parentId,
        menuId = func.menuId
    )
}
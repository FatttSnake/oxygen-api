package top.fatweb.oxygen.api.converter.permission

import top.fatweb.oxygen.api.entity.permission.Func
import top.fatweb.oxygen.api.vo.permission.base.FuncVo

/**
 * Function converter
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
object FuncConverter {
    /**
     * Convert Func object into FuncVo object
     *
     * @param func Func object
     * @return FuncVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see Func
     * @see FuncVo
     */
    fun funcToFuncVo(func: Func) = FuncVo(
        id = func.id,
        name = func.name,
        parentId = func.parentId,
        menuId = func.menuId
    )
}
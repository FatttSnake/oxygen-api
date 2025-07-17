package top.fatweb.oxygen.api.converter.permission

import top.fatweb.oxygen.api.entity.permission.*
import top.fatweb.oxygen.api.vo.permission.PowerSetVo

/**
 * Convert to PowerSetVo object
 *
 * @return PowerSetVo object
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.1.0
 * @see PowerSet
 * @see PowerSetVo
 */
fun PowerSet.toVo() = PowerSetVo(
    moduleList = this.moduleList?.map(Module::toVo),
    menuList = this.menuList?.map(Menu::toVo),
    funcList = this.funcList?.map(Func::toVo),
    operationList = this.operationList?.map(Operation::toVo)
)

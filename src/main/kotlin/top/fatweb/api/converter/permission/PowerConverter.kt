package top.fatweb.api.converter.permission

import top.fatweb.api.entity.permission.PowerSet
import top.fatweb.api.vo.permission.PowerSetVo

/**
 * Power converter
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
object PowerConverter {
    fun powerSetToPowerSetVo(powerSet: PowerSet) = PowerSetVo(
        moduleList = powerSet.moduleList?.map { ModuleConverter.moduleToModuleVo(it) },
        menuList = powerSet.menuList?.map { MenuConverter.menuToMenuVo(it) },
        funcList = powerSet.funcList?.map { FuncConverter.funcToFuncVo(it) },
        operationList = powerSet.operationList?.map { OperationConverter.operationToOperationVo(it) }
    )
}
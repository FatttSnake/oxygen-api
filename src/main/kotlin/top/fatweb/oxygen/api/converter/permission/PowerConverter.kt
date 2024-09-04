package top.fatweb.oxygen.api.converter.permission

import top.fatweb.oxygen.api.entity.permission.PowerSet
import top.fatweb.oxygen.api.vo.permission.PowerSetVo

/**
 * Power converter
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
object PowerConverter {
    /**
     * Convert PowerSet object into PowerSetVo object
     *
     * @param powerSet PowerSet object
     * @return PowerSetVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see PowerSet
     * @see PowerSetVo
     */
    fun powerSetToPowerSetVo(powerSet: PowerSet) = PowerSetVo(
        moduleList = powerSet.moduleList?.map(ModuleConverter::moduleToModuleVo),
        menuList = powerSet.menuList?.map(MenuConverter::menuToMenuVo),
        funcList = powerSet.funcList?.map(FuncConverter::funcToFuncVo),
        operationList = powerSet.operationList?.map(OperationConverter::operationToOperationVo)
    )
}
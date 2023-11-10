package top.fatweb.api.converter.permission

import top.fatweb.api.entity.permission.PowerSet
import top.fatweb.api.vo.permission.PowerSetVo

object PowerConverter {
    fun powerSetToPowerSetVo(powerSet: PowerSet) = PowerSetVo(
        moduleList = powerSet.moduleList?.map { ModuleConverter.moduleToModuleVo(it) },
        menuList = powerSet.menuList?.map { MenuConverter.menuToMenuVo(it) },
        elementList = powerSet.elementList?.map { ElementConverter.elementToElementVo(it) },
        operationList = powerSet.operationList?.map { OperationConverter.operationToOperationVo(it) }
    )
}
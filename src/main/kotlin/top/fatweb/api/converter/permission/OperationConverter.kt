package top.fatweb.api.converter.permission

import top.fatweb.api.entity.permission.Operation
import top.fatweb.api.vo.permission.OperationVo

object OperationConverter {
    fun operationToOperationVo(operation: Operation) = OperationVo(
        id = operation.id,
        name = operation.name,
        code = operation.code,
        powerId = operation.powerId,
        elementId = operation.elementId
    )
}
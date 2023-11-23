package top.fatweb.api.converter.permission

import top.fatweb.api.entity.permission.Operation
import top.fatweb.api.vo.permission.OperationVo

/**
 * Operation converter
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
object OperationConverter {
    fun operationToOperationVo(operation: Operation) = OperationVo(
        id = operation.id,
        name = operation.name,
        code = operation.code,
        elementId = operation.elementId
    )
}
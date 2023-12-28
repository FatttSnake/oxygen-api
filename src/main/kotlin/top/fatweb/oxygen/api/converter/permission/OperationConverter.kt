package top.fatweb.oxygen.api.converter.permission

import top.fatweb.oxygen.api.entity.permission.Operation
import top.fatweb.oxygen.api.vo.permission.base.OperationVo

/**
 * Operation converter
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
object OperationConverter {
    /**
     * Convert Operation object into OperationVo object
     *
     * @param operation Operation object
     * @return OperationVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see Operation
     * @see OperationVo
     */
    fun operationToOperationVo(operation: Operation) = OperationVo(
        id = operation.id,
        name = operation.name,
        code = operation.code,
        funcId = operation.funcId
    )
}
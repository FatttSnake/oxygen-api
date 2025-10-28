package top.fatweb.oxygen.api.converter.permission

import top.fatweb.oxygen.api.entity.permission.Operation
import top.fatweb.oxygen.api.vo.permission.base.OperationVo

/**
 * Convert to OperationVo object
 *
 * @return OperationVo object
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.1.0
 * @see Operation
 * @see OperationVo
 */
fun Operation.toVo() = OperationVo(
    id = this.id,
    name = this.name,
    code = this.code,
    funcId = this.funcId
)

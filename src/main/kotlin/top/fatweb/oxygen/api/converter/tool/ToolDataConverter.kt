package top.fatweb.oxygen.api.converter.tool

import top.fatweb.oxygen.api.entity.tool.ToolData
import top.fatweb.oxygen.api.vo.tool.ToolDataVo

/**
 * Convert to ToolDataVo object
 *
 * @return ToolDataVo object
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.1.0
 * @see ToolData
 * @see ToolDataVo
 */
fun ToolData.toVo() = ToolDataVo(
    id = this.id,
    data = this.data,
    createTime = this.createTime,
    updateTime = this.updateTime
)

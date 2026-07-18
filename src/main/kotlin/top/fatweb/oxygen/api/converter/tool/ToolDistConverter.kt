package top.fatweb.oxygen.api.converter.tool

import top.fatweb.oxygen.api.entity.tool.ToolDist
import top.fatweb.oxygen.api.vo.tool.ToolDistVo

/**
 * Convert to ToolDistVo object
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.3.0
 * @see ToolDist
 * @see ToolDistVo
 */
fun ToolDist.toVo() = ToolDistVo(
    id = this.id,
    fileContent = this.fileContent,
    fileSize = this.fileSize,
    createTime = this.createTime,
    updateTime = this.updateTime,
)

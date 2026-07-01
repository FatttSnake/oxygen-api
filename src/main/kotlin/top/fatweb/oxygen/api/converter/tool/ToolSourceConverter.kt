package top.fatweb.oxygen.api.converter.tool

import top.fatweb.oxygen.api.entity.tool.ToolSource
import top.fatweb.oxygen.api.vo.tool.ToolSourceVo

/**
 * Convert to ToolSourceVo object
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.2.0
 * @see ToolSource
 * @see ToolSourceVo
 */
fun ToolSource.toVo() = ToolSourceVo(
    id = this.id,
    rootId = this.rootId,
    parentId = this.parentId,
    fileName = this.fileName,
    rootNode = this.rootNode == 1,
    dirNode = this.dirNode == 1,
    createTime = this.createTime,
    updateTime = this.updateTime,
    latestFileVersion = this.latestFileVersion?.toVo()
)

package top.fatweb.oxygen.api.converter.tool

import top.fatweb.oxygen.api.entity.tool.ToolFileVersion
import top.fatweb.oxygen.api.vo.tool.ToolFileVersionVo

/**
 * Convert to ToolFileVersionVo object
 *
 * @return ToolFileVersionVo object
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.2.0
 * @see ToolFileVersion
 * @see ToolFileVersionVo
 */
fun ToolFileVersion.toVo() = ToolFileVersionVo(
    id = this.id,
    nodeId = this.nodeId,
    ver = this.ver,
    fileContent = this.fileContent,
    fileSize = this.fileSize,
    createTime = this.createTime,
    updateTime = this.updateTime,
)

package top.fatweb.oxygen.api.converter.tool

import top.fatweb.oxygen.api.entity.tool.ToolData
import top.fatweb.oxygen.api.vo.tool.ToolDataVo

/**
 * Tool data converter
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
object ToolDataConverter {
    /**
     * Convert ToolData object into ToolDataVo object
     *
     * @param toolData ToolData object
     * @return ToolDataVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolData
     * @see ToolDataVo
     */
    fun toolDataToToolDataVo(toolData: ToolData) = ToolDataVo(
        id = toolData.id,
        data = toolData.data,
        createTime = toolData.createTime,
        updateTime = toolData.updateTime
    )
}
package top.fatweb.oxygen.api.converter.tool

import top.fatweb.oxygen.api.entity.tool.ToolData
import top.fatweb.oxygen.api.vo.tool.ToolDataVo

object ToolDataConverter {
    fun toolDataToToolDataVo(toolData: ToolData) = ToolDataVo(
        id = toolData.id,
        data = toolData.data,
        createTime = toolData.createTime,
        updateTime = toolData.updateTime
    )
}
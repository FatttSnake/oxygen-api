package top.fatweb.oxygen.api.converter.tool

import top.fatweb.oxygen.api.entity.tool.ToolBase
import top.fatweb.oxygen.api.param.tool.ToolBaseAddParam
import top.fatweb.oxygen.api.vo.tool.ToolBaseVo

object ToolBaseConverter {
    fun toolBaseToToolBaseVo(toolBase: ToolBase) = ToolBaseVo(
        id = toolBase.id,
        name = toolBase.name,
        source = toolBase.source?.let(ToolDataConverter::toolDataToToolDataVo),
        dist = toolBase.dist?.let(ToolDataConverter::toolDataToToolDataVo),
        createTime = toolBase.createTime,
        updateTime = toolBase.updateTime
    )
}
package top.fatweb.oxygen.api.converter.tool

import top.fatweb.oxygen.api.entity.tool.ToolBase
import top.fatweb.oxygen.api.vo.tool.ToolBaseVo
import top.fatweb.oxygen.api.vo.tool.ToolDataVo

object ToolBaseConverter {
    fun toolBaseToToolBaseVo(toolBase: ToolBase) = ToolBaseVo(
        id = toolBase.id,
        name = toolBase.name,
        source = toolBase.source?.let(ToolDataConverter::toolDataToToolDataVo),
        dist = toolBase.dist?.let(ToolDataConverter::toolDataToToolDataVo),
        compiled = toolBase.compiled == 1,
        createTime = toolBase.createTime,
        updateTime = toolBase.updateTime,
        enable = toolBase.enable == 1
    )

    fun toolBaseToToolBaseVoByGetList(toolBase: ToolBase) = ToolBaseVo(
        id = toolBase.id,
        name = toolBase.name,
        source = ToolDataVo(id = toolBase.sourceId, data = null, createTime = null, updateTime = null),
        dist = ToolDataVo(id = toolBase.distId, data = null, createTime = null, updateTime = null),
        compiled = toolBase.compiled == 1,
        createTime = toolBase.createTime,
        updateTime = toolBase.updateTime,
        enable = toolBase.enable == 1
    )
}
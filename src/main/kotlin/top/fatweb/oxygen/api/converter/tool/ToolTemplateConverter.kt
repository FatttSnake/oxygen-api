package top.fatweb.oxygen.api.converter.tool

import top.fatweb.oxygen.api.entity.tool.ToolTemplate
import top.fatweb.oxygen.api.vo.tool.ToolTemplateVo

object ToolTemplateConverter {
    fun toolTemplateToToolTemplateVo(toolTemplate: ToolTemplate) = ToolTemplateVo(
        id = toolTemplate.id,
        name = toolTemplate.name,
        base = toolTemplate.base?.let(ToolBaseConverter::toolBaseToToolBaseVo),
        source = toolTemplate.source?.let(ToolDataConverter::toolDataToToolDataVo),
        createTime = toolTemplate.createTime,
        updateTime = toolTemplate.updateTime,
        enable = toolTemplate.enable == 1
    )
}
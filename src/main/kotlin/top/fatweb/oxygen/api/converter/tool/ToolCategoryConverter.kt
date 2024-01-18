package top.fatweb.oxygen.api.converter.tool

import top.fatweb.oxygen.api.entity.tool.ToolCategory
import top.fatweb.oxygen.api.param.tool.ToolCategoryAddParam
import top.fatweb.oxygen.api.param.tool.ToolCategoryUpdateParam
import top.fatweb.oxygen.api.vo.tool.ToolCategoryVo

object ToolCategoryConverter {
    fun toolCategoryToToolCategoryVo(toolCategory: ToolCategory) = ToolCategoryVo(
        id = toolCategory.id,
        name = toolCategory.name,
        enable = toolCategory.enable == 1,
        createTime = toolCategory.createTime,
        updateTime = toolCategory.updateTime
    )

    fun toolCategoryAddParamToToolCategory(toolCategoryAddParam: ToolCategoryAddParam) = ToolCategory().apply {
        name = toolCategoryAddParam.name
        enable = if (toolCategoryAddParam.enable) 1 else 0
    }

    fun toolCategoryUpdateParamToToolCategory(toolCategoryUpdateParam: ToolCategoryUpdateParam) = ToolCategory().apply {
        id = toolCategoryUpdateParam.id
        name = toolCategoryUpdateParam.name
        enable = toolCategoryUpdateParam.enable?. let { if (it) 1 else 0 }
    }
}
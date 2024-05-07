package top.fatweb.oxygen.api.converter.tool

import top.fatweb.oxygen.api.entity.tool.ToolCategory
import top.fatweb.oxygen.api.param.tool.ToolCategoryAddParam
import top.fatweb.oxygen.api.param.tool.ToolCategoryUpdateParam
import top.fatweb.oxygen.api.vo.tool.ToolCategoryVo

/**
 * Tool category converter
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
object ToolCategoryConverter {
    /**
     * Convert ToolCategory object into ToolCategoryVo object
     *
     * @param toolCategory ToolCategory object
     * @return ToolCategoryVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolCategory
     * @see ToolCategoryVo
     */
    fun toolCategoryToToolCategoryVo(toolCategory: ToolCategory) = ToolCategoryVo(
        id = toolCategory.id,
        name = toolCategory.name,
        enable = toolCategory.enable?.let { it == 1},
        createTime = toolCategory.createTime,
        updateTime = toolCategory.updateTime
    )

    /**
     * Convert ToolCategoryAddParam object into ToolCategory object
     *
     * @param toolCategoryAddParam ToolCategoryAddParam object
     * @return ToolCateGory object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolCategoryAddParam
     * @see ToolCategory
     */
    fun toolCategoryAddParamToToolCategory(toolCategoryAddParam: ToolCategoryAddParam) = ToolCategory().apply {
        name = toolCategoryAddParam.name
        enable = if (toolCategoryAddParam.enable) 1 else 0
    }

    /**
     * Convert ToolCategoryUpdateParam object into ToolCategory object
     *
     * @param toolCategoryUpdateParam ToolCategoryUpdateParam object
     * @return ToolCategory object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see ToolCategoryUpdateParam
     * @see ToolCategory
     */
    fun toolCategoryUpdateParamToToolCategory(toolCategoryUpdateParam: ToolCategoryUpdateParam) = ToolCategory().apply {
        id = toolCategoryUpdateParam.id
        name = toolCategoryUpdateParam.name
        enable = toolCategoryUpdateParam.enable?. let { if (it) 1 else 0 }
    }
}
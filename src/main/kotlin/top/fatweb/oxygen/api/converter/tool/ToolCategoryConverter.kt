package top.fatweb.oxygen.api.converter.tool

import top.fatweb.oxygen.api.entity.tool.ToolCategory
import top.fatweb.oxygen.api.param.tool.ToolCategoryAddParam
import top.fatweb.oxygen.api.param.tool.ToolCategoryUpdateParam
import top.fatweb.oxygen.api.vo.tool.ToolCategoryVo

/**
 * Convert to ToolCategoryVo object
 *
 * @return ToolCategoryVo object
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.1.0
 * @see ToolCategory
 * @see ToolCategoryVo
 */
fun ToolCategory.toVo() = ToolCategoryVo(
    id = this.id,
    name = this.name,
    enable = this.enable?.let { it == 1 },
    createTime = this.createTime,
    updateTime = this.updateTime
)

/**
 * Convert to ToolCategory object
 *
 * @return ToolCateGory object
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.1.0
 * @see ToolCategoryAddParam
 * @see ToolCategory
 */
fun ToolCategoryAddParam.toEntity() = ToolCategory().apply {
    name = this@toEntity.name
    enable = if (this@toEntity.enable) 1 else 0
}

/**
 * Convert to ToolCategory object
 *
 * @return ToolCategory object
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.1.0
 * @see ToolCategoryUpdateParam
 * @see ToolCategory
 */
fun ToolCategoryUpdateParam.toEntity() = ToolCategory().apply {
    id = this@toEntity.id
    name = this@toEntity.name
    enable = this@toEntity.enable?.let { if (it) 1 else 0 }
}

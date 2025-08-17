package top.fatweb.oxygen.api.converter.tool

import com.baomidou.mybatisplus.core.metadata.IPage
import top.fatweb.oxygen.api.converter.permission.toVoWithInfo
import top.fatweb.oxygen.api.entity.permission.User
import top.fatweb.oxygen.api.entity.tool.Tool
import top.fatweb.oxygen.api.entity.tool.ToolCategory
import top.fatweb.oxygen.api.entity.tool.ToolData
import top.fatweb.oxygen.api.param.tool.ToolUpdateParam
import top.fatweb.oxygen.api.vo.PageVo
import top.fatweb.oxygen.api.vo.tool.ToolVo
import top.fatweb.oxygen.api.vo.tool.ToolWithDistVo
import top.fatweb.oxygen.api.vo.tool.ToolWithSourceVo

/**
 * Convert to ToolVo object
 *
 * @return ToolVo object
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.1.0
 * @see Tool
 * @see ToolVo
 */
fun Tool.toVo() = ToolVo(
    id = this.id,
    name = this.name,
    toolId = this.toolId,
    icon = this.icon,
    platform = this.platform,
    description = this.description,
    baseId = this.baseId,
    baseVersion = this.baseVersion,
    author = this.author?.let(User::toVoWithInfo),
    ver = this.ver,
    keywords = this.keywords,
    categories = this.categories?.map(ToolCategory::toVo),
    entryPoint = this.entryPoint,
    publish = this.publish,
    review = this.review,
    createTime = this.createTime,
    updateTime = this.updateTime,
    favorite = this.favorite?.let { it == 1 }
)

/**
 * Convert to PageVo<ToolVo> object
 *
 * @return PageVo<ToolVo> object
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.1.0
 * @see IPage
 * @see Tool
 * @see PageVo
 */
fun IPage<Tool>.toVo() = PageVo(
    total = this.total,
    pages = this.pages,
    size = this.size,
    current = this.current,
    records = this.records.map(Tool::toVo)
)

/**
 * Convert to ToolWithSourceVo object
 *
 * @return ToolWithSourceVo object
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.1.0
 * @see Tool
 * @see ToolWithSourceVo
 */
fun Tool.toVoWithSource() = ToolWithSourceVo(
    id = this.id,
    name = this.name,
    toolId = this.toolId,
    icon = this.icon,
    platform = this.platform,
    description = this.description,
    baseId = this.baseId,
    baseVersion = this.baseVersion,
    author = this.author?.let(User::toVoWithInfo),
    ver = this.ver,
    keywords = this.keywords,
    categories = this.categories?.map(ToolCategory::toVo),
    source = this.source?.let(ToolData::toVo),
    entryPoint = this.entryPoint,
    publish = this.publish,
    review = this.review,
    createTime = this.createTime,
    updateTime = this.updateTime,
    favorite = this.favorite?.let { it == 1 }
)

/**
 * Convert to ToolWithDistVo object
 *
 * @return ToolWithDistVo object
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.1.0
 * @see Tool
 * @see ToolWithDistVo
 */
fun Tool.toVoWithDist() = ToolWithDistVo(
    id = this.id,
    name = this.name,
    toolId = this.toolId,
    icon = this.icon,
    platform = this.platform,
    description = this.description,
    baseId = this.baseId,
    baseVersion = this.baseVersion,
    author = this.author?.let(User::toVoWithInfo),
    ver = this.ver,
    keywords = this.keywords,
    categories = this.categories?.map(ToolCategory::toVo),
    dist = this.dist?.let(ToolData::toVo),
    entryPoint = this.entryPoint,
    publish = this.publish,
    review = this.review,
    createTime = this.createTime,
    updateTime = this.updateTime,
    favorite = this.favorite?.let { it == 1 }
)

/**
 * Convert to Tool object
 *
 * @return Tool object
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.1.0
 * @see ToolUpdateParam
 * @see Tool
 */
fun ToolUpdateParam.toEntity() = Tool().apply {
    id = this@toEntity.id
    name = this@toEntity.name
    icon = this@toEntity.icon
    description = this@toEntity.description
    keywords = this@toEntity.keywords
}

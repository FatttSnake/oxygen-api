package top.fatweb.oxygen.api.converter.tool

import com.baomidou.mybatisplus.core.metadata.IPage
import top.fatweb.oxygen.api.entity.tool.ToolBase
import top.fatweb.oxygen.api.entity.tool.ToolData
import top.fatweb.oxygen.api.entity.tool.ToolTemplate
import top.fatweb.oxygen.api.param.tool.ToolTemplateAddParam
import top.fatweb.oxygen.api.param.tool.ToolTemplateUpdateParam
import top.fatweb.oxygen.api.vo.PageVo
import top.fatweb.oxygen.api.vo.tool.ToolTemplateVo
import top.fatweb.oxygen.api.vo.tool.ToolTemplateWithSourceVo

/**
 * Convert to ToolTemplateVo object
 *
 * @return ToolTemplateVo object
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.1.0
 * @see ToolTemplate
 * @see ToolTemplateVo
 */
fun ToolTemplate.toVo() = ToolTemplateVo(
    id = this.id,
    name = this.name,
    base = this.base?.let(ToolBase::toVo),
    platform = this.platform,
    entryPoint = this.entryPoint,
    enable = this.enable?.let { it == 1 },
    createTime = this.createTime,
    updateTime = this.updateTime
)

/**
 * Convert to PageVo<ToolTemplateVo> object
 *
 * @return PageVo<ToolTemplateVo> object
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.1.0
 * @see IPage
 * @see ToolTemplate
 * @see PageVo
 */
fun IPage<ToolTemplate>.toVo() = PageVo(
    total = this.total,
    pages = this.pages,
    size = this.size,
    current = this.current,
    records = this.records.map(ToolTemplate::toVo)
)

/**
 * Convert to ToolTemplateWithSourceVo object with base dist
 *
 * @return ToolTemplateWithSourceVo object
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.1.0
 * @see ToolTemplate
 * @see ToolTemplateWithSourceVo
 */
fun ToolTemplate.toVoWithSource() = ToolTemplateWithSourceVo(
    id = this.id,
    name = this.name,
    base = this.base?.let(ToolBase::toVo),
    source = this.source?.let(ToolData::toVo),
    platform = this.platform,
    entryPoint = this.entryPoint,
    enable = this.enable?.let { it == 1 },
    createTime = this.createTime,
    updateTime = this.updateTime
)

/**
 * Convert to ToolTemplate object
 *
 * @return ToolTemplate object
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.1.0
 * @see ToolTemplateAddParam
 * @see ToolTemplate
 */
fun ToolTemplateAddParam.toEntity() = ToolTemplate().apply {
    name = this@toEntity.name
    baseId = this@toEntity.baseId
    baseVersion = this@toEntity.baseVersion
    entryPoint = this@toEntity.entryPoint
    enable = if (this@toEntity.enable) 1 else 0
}

/**
 * Convert to ToolTemplate object
 *
 * @return ToolTemplate object
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.1.0
 * @see ToolTemplateUpdateParam
 * @see ToolTemplate
 */
fun ToolTemplateUpdateParam.toEntity() = ToolTemplate().apply {
    id = this@toEntity.id
    name = this@toEntity.name
    entryPoint = this@toEntity.entryPoint
    enable = if (this@toEntity.enable) 1 else 0
}

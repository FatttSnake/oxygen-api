package top.fatweb.oxygen.api.converter.tool

import com.baomidou.mybatisplus.core.metadata.IPage
import top.fatweb.oxygen.api.entity.tool.ToolBase
import top.fatweb.oxygen.api.entity.tool.ToolData
import top.fatweb.oxygen.api.entity.tool.ToolTemplate
import top.fatweb.oxygen.api.vo.PageVo
import top.fatweb.oxygen.api.vo.tool.ToolBaseVo
import top.fatweb.oxygen.api.vo.tool.ToolDataVo
import top.fatweb.oxygen.api.vo.tool.ToolTemplateVo

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
    source = this.source?.let(ToolData::toVo),
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
 * Convert to ToolTemplateVo object by list
 *
 * @return ToolTemplateVo object
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.1.0
 * @see ToolTemplate
 * @see ToolTemplateVo
 */
fun ToolTemplate.toVoByList() = ToolTemplateVo(
    id = this.id,
    name = this.name,
    base = ToolBaseVo(
        id = this.baseId,
        name = null,
        source = null,
        dist = null,
        platform = this.base?.platform,
        compiled = null,
        createTime = null,
        updateTime = null
    ),
    source = ToolDataVo(id = this.sourceId, data = null, createTime = null, updateTime = null),
    platform = this.platform,
    entryPoint = this.entryPoint,
    enable = this.enable?.let { it == 1 },
    createTime = this.createTime,
    updateTime = this.updateTime
)

/**
 * Convert to ToolTemplateVo object with base dist
 *
 * @return ToolTemplateVo object
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.1.0
 * @see ToolTemplate
 * @see ToolTemplateVo
 */
fun ToolTemplate.toVoWithBaseDist() = ToolTemplateVo(
    id = this.id,
    name = this.name,
    base = ToolBaseVo(
        id = this.baseId,
        name = this.base?.name,
        source = null,
        dist = ToolDataVo(id = null, data = this.base?.distData, createTime = null, updateTime = null),
        platform = this.base?.platform,
        compiled = null,
        createTime = null,
        updateTime = null
    ),
    source = this.source?.let(ToolData::toVo),
    platform = this.platform,
    entryPoint = this.entryPoint,
    enable = this.enable?.let { it == 1 },
    createTime = this.createTime,
    updateTime = this.updateTime
)

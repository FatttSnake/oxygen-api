package top.fatweb.oxygen.api.service.tool.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.oxygen.api.converter.tool.toEntity
import top.fatweb.oxygen.api.converter.tool.toVo
import top.fatweb.oxygen.api.entity.tool.ToolCategory
import top.fatweb.oxygen.api.mapper.tool.ToolCategoryMapper
import top.fatweb.oxygen.api.param.tool.ToolCategoryAddParam
import top.fatweb.oxygen.api.param.tool.ToolCategoryUpdateParam
import top.fatweb.oxygen.api.service.tool.IToolCategoryService
import top.fatweb.oxygen.api.util.queryOrThrowException
import top.fatweb.oxygen.api.util.saveOrThrowException
import top.fatweb.oxygen.api.util.updateOrThrowException
import top.fatweb.oxygen.api.vo.tool.ToolCategoryVo

/**
 * Tool category service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see ServiceImpl
 * @see ToolCategoryMapper
 * @see ToolCategory
 * @see IToolCategoryService
 */
@Service
class ToolCategoryServiceImpl : ServiceImpl<ToolCategoryMapper, ToolCategory>(), IToolCategoryService {
    override fun getOne(id: Long): ToolCategoryVo =
        queryOrThrowException { this.getById(id) }.let(ToolCategory::toVo)

    override fun get(): List<ToolCategoryVo> =
        this.list().map(ToolCategory::toVo)

    override fun add(toolCategoryAddParam: ToolCategoryAddParam): ToolCategoryVo {
        val toolCategory = toolCategoryAddParam.toEntity()

        saveOrThrowException { this.save(toolCategory) }

        return toolCategory.toVo()
    }

    override fun update(toolCategoryUpdateParam: ToolCategoryUpdateParam): ToolCategoryVo {
        val toolCategory = toolCategoryUpdateParam.toEntity()

        updateOrThrowException { this.updateById(toolCategory) }

        return toolCategory.toVo()
    }

    override fun delete(id: Long): Boolean = this.removeById(id)
}

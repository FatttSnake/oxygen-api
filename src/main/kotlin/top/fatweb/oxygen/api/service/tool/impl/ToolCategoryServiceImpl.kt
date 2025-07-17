package top.fatweb.oxygen.api.service.tool.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.oxygen.api.converter.tool.toEntity
import top.fatweb.oxygen.api.converter.tool.toVo
import top.fatweb.oxygen.api.entity.tool.ToolCategory
import top.fatweb.oxygen.api.exception.DatabaseInsertException
import top.fatweb.oxygen.api.exception.DatabaseUpdateException
import top.fatweb.oxygen.api.exception.NoRecordFoundException
import top.fatweb.oxygen.api.mapper.tool.ToolCategoryMapper
import top.fatweb.oxygen.api.param.tool.ToolCategoryAddParam
import top.fatweb.oxygen.api.param.tool.ToolCategoryUpdateParam
import top.fatweb.oxygen.api.service.tool.IToolCategoryService
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
        this.getById(id)?.let(ToolCategory::toVo) ?: throw NoRecordFoundException()

    override fun get(): List<ToolCategoryVo> =
        this.list().map(ToolCategory::toVo)

    override fun add(toolCategoryAddParam: ToolCategoryAddParam): ToolCategoryVo {
        val toolCategory = toolCategoryAddParam.toEntity()

        if (!this.save(toolCategory)) {
            throw DatabaseInsertException()
        }

        return toolCategory.toVo()
    }

    override fun update(toolCategoryUpdateParam: ToolCategoryUpdateParam): ToolCategoryVo {
        val toolCategory = toolCategoryUpdateParam.toEntity()

        if (!this.updateById(toolCategory)) {
            throw DatabaseUpdateException()
        }

        return toolCategory.toVo()
    }

    override fun delete(id: Long): Boolean = this.removeById(id)
}

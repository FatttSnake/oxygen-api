package top.fatweb.oxygen.api.service.tool.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.oxygen.api.converter.tool.ToolCategoryConverter
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
        this.getById(id)?.let(ToolCategoryConverter::toolCategoryToToolCategoryVo) ?: throw NoRecordFoundException()

    override fun get(): List<ToolCategoryVo> =
        this.list().map(ToolCategoryConverter::toolCategoryToToolCategoryVo)

    override fun add(toolCategoryAddParam: ToolCategoryAddParam): ToolCategoryVo {
        val toolCategory = ToolCategoryConverter.toolCategoryAddParamToToolCategory(toolCategoryAddParam)

        if (!this.save(toolCategory)) {
            throw DatabaseInsertException()
        }

        return ToolCategoryConverter.toolCategoryToToolCategoryVo(toolCategory)
    }

    override fun update(toolCategoryUpdateParam: ToolCategoryUpdateParam): ToolCategoryVo {
        val toolCategory = ToolCategoryConverter.toolCategoryUpdateParamToToolCategory(toolCategoryUpdateParam)

        if (this.updateById(toolCategory)) {
            throw DatabaseUpdateException()
        }

        return ToolCategoryConverter.toolCategoryToToolCategoryVo(toolCategory)
    }

    override fun delete(id: Long): Boolean = this.removeById(id)
}
package top.fatweb.oxygen.api.service.tool

import com.baomidou.mybatisplus.extension.service.IService
import top.fatweb.oxygen.api.entity.tool.ToolCategory
import top.fatweb.oxygen.api.param.tool.ToolCategoryAddParam
import top.fatweb.oxygen.api.param.tool.ToolCategoryUpdateParam
import top.fatweb.oxygen.api.vo.tool.ToolCategoryVo

/**
 * Tool category service interface
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see IService
 * @see ToolCategory
 */
interface IToolCategoryService : IService<ToolCategory> {
    fun getOne(id: Long): ToolCategoryVo

    fun get(): List<ToolCategoryVo>

    fun add(toolCategoryAddParam: ToolCategoryAddParam): ToolCategoryVo

    fun update(toolCategoryUpdateParam: ToolCategoryUpdateParam): ToolCategoryVo

    fun delete(id: Long): Boolean
}
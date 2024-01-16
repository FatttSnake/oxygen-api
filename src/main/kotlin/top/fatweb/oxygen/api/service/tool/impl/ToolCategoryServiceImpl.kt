package top.fatweb.oxygen.api.service.tool.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import top.fatweb.oxygen.api.entity.tool.ToolCategory
import top.fatweb.oxygen.api.mapper.tool.ToolCategoryMapper
import top.fatweb.oxygen.api.service.tool.IToolCategoryService

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
class ToolCategoryServiceImpl : ServiceImpl<ToolCategoryMapper, ToolCategory>(), IToolCategoryService
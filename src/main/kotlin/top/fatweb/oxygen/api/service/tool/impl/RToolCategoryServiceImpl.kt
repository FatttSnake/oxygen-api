package top.fatweb.oxygen.api.service.tool.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import top.fatweb.oxygen.api.entity.tool.RToolCategory
import top.fatweb.oxygen.api.mapper.tool.RToolCategoryMapper
import top.fatweb.oxygen.api.service.tool.IRToolCategoryService

/**
 * Tool category service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see ServiceImpl
 * @see RToolCategoryMapper
 * @see RToolCategory
 * @see IRToolCategoryService
 */
class RToolCategoryServiceImpl : ServiceImpl<RToolCategoryMapper, RToolCategory>(), IRToolCategoryService
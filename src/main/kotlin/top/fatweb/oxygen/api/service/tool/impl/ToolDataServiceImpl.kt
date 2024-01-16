package top.fatweb.oxygen.api.service.tool.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import top.fatweb.oxygen.api.entity.tool.ToolData
import top.fatweb.oxygen.api.mapper.tool.ToolDataMapper
import top.fatweb.oxygen.api.service.tool.IToolDataService

/**
 * Tool data service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see ServiceImpl
 * @see ToolDataMapper
 * @see ToolData
 * @see IToolDataService
 */
class ToolDataServiceImpl : ServiceImpl<ToolDataMapper, ToolData>(), IToolDataService
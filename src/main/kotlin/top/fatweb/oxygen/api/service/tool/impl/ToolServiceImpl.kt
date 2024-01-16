package top.fatweb.oxygen.api.service.tool.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import top.fatweb.oxygen.api.entity.tool.Tool
import top.fatweb.oxygen.api.mapper.tool.ToolMapper
import top.fatweb.oxygen.api.service.tool.IToolService

/**
 * Tool service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see ServiceImpl
 * @see ToolMapper
 * @see Tool
 * @see IToolService
 */
class ToolServiceImpl : ServiceImpl<ToolMapper, Tool>(), IToolService
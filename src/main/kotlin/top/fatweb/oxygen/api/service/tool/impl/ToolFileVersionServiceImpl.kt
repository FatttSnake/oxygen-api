package top.fatweb.oxygen.api.service.tool.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.oxygen.api.entity.tool.ToolFileVersion
import top.fatweb.oxygen.api.mapper.tool.ToolFileVersionMapper
import top.fatweb.oxygen.api.service.tool.IToolFileVersionService

/**
 * Tool file version service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.2.0
 * @see ServiceImpl
 * @see ToolFileVersionMapper
 * @see ToolFileVersion
 * @see IToolFileVersionService
 */
@Service
class ToolFileVersionServiceImpl : ServiceImpl<ToolFileVersionMapper, ToolFileVersion>(), IToolFileVersionService

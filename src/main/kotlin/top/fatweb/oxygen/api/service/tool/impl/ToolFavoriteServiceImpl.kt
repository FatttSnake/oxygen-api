package top.fatweb.oxygen.api.service.tool.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.oxygen.api.entity.tool.ToolFavorite
import top.fatweb.oxygen.api.mapper.tool.ToolFavoriteMapper
import top.fatweb.oxygen.api.service.tool.IToolFavoriteService

@Service
class ToolFavoriteServiceImpl : ServiceImpl<ToolFavoriteMapper, ToolFavorite>(), IToolFavoriteService
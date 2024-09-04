package top.fatweb.oxygen.api.service.permission.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.oxygen.api.entity.permission.Module
import top.fatweb.oxygen.api.mapper.permission.ModuleMapper
import top.fatweb.oxygen.api.service.permission.IModuleService

/**
 * Module service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see ServiceImpl
 * @see ModuleMapper
 * @see Module
 * @see IModuleService
 */
@Service
class ModuleServiceImpl : ServiceImpl<ModuleMapper, Module>(), IModuleService

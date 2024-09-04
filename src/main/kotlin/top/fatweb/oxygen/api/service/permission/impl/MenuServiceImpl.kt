package top.fatweb.oxygen.api.service.permission.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.oxygen.api.entity.permission.Menu
import top.fatweb.oxygen.api.mapper.permission.MenuMapper
import top.fatweb.oxygen.api.service.permission.IMenuService

/**
 * Menu service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see ServiceImpl
 * @see MenuMapper
 * @see Menu
 * @see IMenuService
 */
@Service
class MenuServiceImpl : ServiceImpl<MenuMapper, Menu>(), IMenuService

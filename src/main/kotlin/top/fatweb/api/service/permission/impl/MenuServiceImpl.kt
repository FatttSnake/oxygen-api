package top.fatweb.api.service.permission.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.api.entity.permission.Menu
import top.fatweb.api.mapper.permission.MenuMapper
import top.fatweb.api.service.permission.IMenuService

/**
 * Menu service implement
 *
 * @author FatttSnake
 * @since 1.0.0
 */
@Service
class MenuServiceImpl : ServiceImpl<MenuMapper, Menu>(), IMenuService

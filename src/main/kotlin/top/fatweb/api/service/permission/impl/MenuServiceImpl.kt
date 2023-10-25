package top.fatweb.api.service.permission.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.api.entity.permission.Menu
import top.fatweb.api.mapper.permission.MenuMapper
import top.fatweb.api.service.permission.IMenuService

/**
 * <p>
 * 菜单 服务实现类
 * </p>
 *
 * @author FatttSnake
 * @since 2023-10-25
 */
@Service
class MenuServiceImpl : ServiceImpl<MenuMapper, Menu>(), IMenuService

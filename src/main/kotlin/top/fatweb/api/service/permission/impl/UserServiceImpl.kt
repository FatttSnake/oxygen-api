package top.fatweb.api.service.permission.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.api.entity.permission.User
import top.fatweb.api.mapper.permission.UserMapper
import top.fatweb.api.service.permission.*
import top.fatweb.api.util.WebUtil

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author FatttSnake
 * @since 2023-10-04
 */
@Service
class UserServiceImpl(
    private val moduleService: IModuleService,
    private val menuService: IMenuService,
    private val elementService: IElementService,
    private val operationService: IOperationService
) : ServiceImpl<UserMapper, User>(), IUserService {
    override fun getUserWithPower(username: String): User? {
        val user = baseMapper.getOneWithPowerInfoByUsername(username)
        user ?: let { return null }

        if (user.id == 0L) {
            user.modules = moduleService.list()
            user.menus = menuService.list()
            user.elements = elementService.list()
            user.operations = operationService.list()
        }

        return user
    }

    override fun getInfo() = WebUtil.getLoginUsername()?.let { getUserWithPower(it) } ?: let { null }

    override fun getList() = baseMapper.getListWithRoleInfo()
}

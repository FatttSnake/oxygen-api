package top.fatweb.api.service.permission.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.api.entity.permission.Power
import top.fatweb.api.entity.permission.PowerSet
import top.fatweb.api.mapper.permission.PowerMapper
import top.fatweb.api.service.permission.*

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author FatttSnake
 * @since 2023-10-25
 */
@Service
class PowerServiceImpl(
    private val moduleService: IModuleService,
    private val menuService: IMenuService,
    private val elementService: IElementService,
    private val operationService: IOperationService
) : ServiceImpl<PowerMapper, Power>(), IPowerService {
    override fun getAll() = PowerSet().apply {
        moduleList = moduleService.list()
        menuList = menuService.list()
        elementList = elementService.list()
        operationList = operationService.list()
    }
}

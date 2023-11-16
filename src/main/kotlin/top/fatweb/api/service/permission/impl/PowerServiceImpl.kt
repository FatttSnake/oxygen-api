package top.fatweb.api.service.permission.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.api.converter.permission.PowerConverter
import top.fatweb.api.entity.permission.Power
import top.fatweb.api.entity.permission.PowerSet
import top.fatweb.api.mapper.permission.PowerMapper
import top.fatweb.api.service.permission.*

/**
 * Power service implement
 *
 * @author FatttSnake
 * @since 1.0.0
 */
@Service
class PowerServiceImpl(
    private val moduleService: IModuleService,
    private val menuService: IMenuService,
    private val elementService: IElementService,
    private val operationService: IOperationService
) : ServiceImpl<PowerMapper, Power>(), IPowerService {
    override fun getList() = PowerConverter.powerSetToPowerSetVo(PowerSet().apply {
        moduleList = moduleService.list()
        menuList = menuService.list()
        elementList = elementService.list()
        operationList = operationService.list()
    })
}

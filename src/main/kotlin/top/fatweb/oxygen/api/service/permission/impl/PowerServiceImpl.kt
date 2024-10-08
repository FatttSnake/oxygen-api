package top.fatweb.oxygen.api.service.permission.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.oxygen.api.converter.permission.PowerConverter
import top.fatweb.oxygen.api.entity.permission.Power
import top.fatweb.oxygen.api.entity.permission.PowerSet
import top.fatweb.oxygen.api.mapper.permission.PowerMapper
import top.fatweb.oxygen.api.service.permission.*

/**
 * Power service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see IModuleService
 * @see IMenuService
 * @see IFuncService
 * @see IOperationService
 * @see ServiceImpl
 * @see PowerMapper
 * @see Power
 * @see IPowerService
 */
@Service
class PowerServiceImpl(
    private val moduleService: IModuleService,
    private val menuService: IMenuService,
    private val funcService: IFuncService,
    private val operationService: IOperationService
) : ServiceImpl<PowerMapper, Power>(), IPowerService {
    override fun getList() = PowerConverter.powerSetToPowerSetVo(PowerSet().apply {
        moduleList = moduleService.list()
        menuList = menuService.list()
        funcList = funcService.list()
        operationList = operationService.list()
    })
}

package top.fatweb.api.service.permission.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.api.entity.permission.PowerType
import top.fatweb.api.mapper.permission.PowerTypeMapper
import top.fatweb.api.service.permission.IPowerTypeService

/**
 * <p>
 * 权限类型表 服务实现类
 * </p>
 *
 * @author FatttSnake
 * @since 2023-10-25
 */
@Service
class PowerTypeServiceImpl : ServiceImpl<PowerTypeMapper, PowerType>(), IPowerTypeService

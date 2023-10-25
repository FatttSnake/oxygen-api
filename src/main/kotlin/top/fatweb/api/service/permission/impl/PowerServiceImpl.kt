package top.fatweb.api.service.permission.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.fatweb.api.entity.permission.Power
import top.fatweb.api.mapper.permission.PowerMapper
import top.fatweb.api.service.permission.IPowerService

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author FatttSnake
 * @since 2023-10-25
 */
@Service
class PowerServiceImpl : ServiceImpl<PowerMapper, Power>(), IPowerService

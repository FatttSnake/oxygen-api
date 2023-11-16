package top.fatweb.api.service.permission

import com.baomidou.mybatisplus.extension.service.IService
import top.fatweb.api.entity.permission.Power
import top.fatweb.api.vo.permission.PowerSetVo

/**
 * Power service interface
 *
 * @author FatttSnake
 * @since 1.0.0
 */
interface IPowerService : IService<Power> {
    fun getList(): PowerSetVo
}

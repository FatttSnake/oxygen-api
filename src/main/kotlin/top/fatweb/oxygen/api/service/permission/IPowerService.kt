package top.fatweb.oxygen.api.service.permission

import com.baomidou.mybatisplus.extension.service.IService
import top.fatweb.oxygen.api.entity.permission.Power
import top.fatweb.oxygen.api.vo.permission.PowerSetVo

/**
 * Power service interface
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see IService
 * @see Power
 */
interface IPowerService : IService<Power> {
    /**
     * Get all power as list
     *
     * @return powerSetVo object
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see PowerSetVo
     */
    fun getList(): PowerSetVo
}

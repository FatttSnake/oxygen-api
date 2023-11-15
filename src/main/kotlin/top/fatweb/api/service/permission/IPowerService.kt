package top.fatweb.api.service.permission

import com.baomidou.mybatisplus.extension.service.IService
import top.fatweb.api.entity.permission.Power
import top.fatweb.api.vo.permission.PowerSetVo

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author FatttSnake
 * @since 2023-10-25
 */
interface IPowerService : IService<Power> {
    fun getAll(): PowerSetVo
}

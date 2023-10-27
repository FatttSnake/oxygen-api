package top.fatweb.api.service.permission

import com.baomidou.mybatisplus.extension.service.IService
import top.fatweb.api.entity.permission.User

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author FatttSnake
 * @since 2023-10-04
 */
interface IUserService : IService<User> {
    fun getUserWithPower(username: String): User?

    fun getInfo(): User?
}

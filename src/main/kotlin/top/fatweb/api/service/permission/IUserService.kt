package top.fatweb.api.service.permission

import com.baomidou.mybatisplus.extension.service.IService
import top.fatweb.api.entity.permission.User

/**
 * User service interface
 *
 * @author FatttSnake
 * @since 1.0.0
 */
interface IUserService : IService<User> {
    fun getUserWithPower(username: String): User?

    fun getInfo(): User?

    fun getList(): List<User>
}

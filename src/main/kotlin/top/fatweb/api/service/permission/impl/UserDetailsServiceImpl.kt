package top.fatweb.api.service.permission.impl

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import top.fatweb.api.entity.permission.LoginUser
import top.fatweb.api.service.permission.IUserService

/**
 * User details service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Service
class UserDetailsServiceImpl(val userService: IUserService) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userService.getUserWithPowerByUsername(username)
        user ?: let { throw Exception("Username not found") }

        return LoginUser(user)
    }
}
package top.fatweb.oxygen.api.service.permission.impl

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import top.fatweb.oxygen.api.entity.permission.LoginUser
import top.fatweb.oxygen.api.service.permission.IUserService

/**
 * User details service implement
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see IUserService
 * @see UserDetailsService
 */
@Service
class UserDetailsServiceImpl(val userService: IUserService) : UserDetailsService {
    override fun loadUserByUsername(account: String): UserDetails {
        val user = userService.getUserWithPowerByAccount(account)
        user ?: let { throw Exception("Username not found") }

        return LoginUser(user)
    }
}
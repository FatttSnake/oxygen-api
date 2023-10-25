package top.fatweb.api.service.permission.impl

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import top.fatweb.api.entity.permission.LoginUser
import top.fatweb.api.entity.permission.User
import top.fatweb.api.service.permission.IUserService

@Service
class UserDetailsServiceImpl(val userService: IUserService) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val user = userService.getOne(KtQueryWrapper(User()).eq(User::username, username))
        user ?: let { throw Exception("Username not found") }

        return LoginUser(user)
    }
}
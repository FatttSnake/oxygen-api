package top.fatweb.api.config

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import jakarta.annotation.PostConstruct
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.DependsOn
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import top.fatweb.api.entity.permission.User
import top.fatweb.api.entity.permission.UserInfo
import top.fatweb.api.properties.AdminProperties
import top.fatweb.api.service.permission.IUserInfoService
import top.fatweb.api.service.permission.IUserService

/**
 * Application initialization configuration
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@DependsOn("adminProperties")
@Component
class InitConfig(
    private val userService: IUserService,
    private val userInfoService: IUserInfoService,
    private val passwordEncoder: PasswordEncoder
) {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @PostConstruct
    fun init() {
        if (!userService.exists(KtQueryWrapper(User()).eq(User::id, 0))) {
            userInfoService.remove(KtQueryWrapper(UserInfo()).eq(UserInfo::userId, 0))

            val rawPassword = AdminProperties.password ?: let {
                logger.warn("No default administrator password is set, a randomly generated password will be used")
                getRandomPassword(10)
            }
            val encodedPassword = passwordEncoder.encode(rawPassword)

            val user = User().apply {
                id = 0
                username = AdminProperties.username
                password = encodedPassword
                locking = 0
                enable = 1
            }
            val userInfo = UserInfo().apply {
                userId = 0
                nickname = AdminProperties.nickname
                email = AdminProperties.email
            }

            if (userService.save(user) && userInfoService.save(userInfo)) {
                logger.warn("First startup, create administrator - username: admin, password: $rawPassword")
                logger.warn("This information will only be shown once. Please change your password promptly after logging in.")
            }
        }

    }

    private fun getRandomPassword(length: Int): String {
        val characterSet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val password = StringBuilder()

        for (i in 0 until length) {
            password.append(characterSet.random())
        }

        return password.toString()
    }
}
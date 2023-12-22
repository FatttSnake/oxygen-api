package top.fatweb.api.config

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import jakarta.annotation.PostConstruct
import org.apache.velocity.app.VelocityEngine
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
import top.fatweb.api.util.StrUtil
import top.fatweb.avatargenerator.GitHubAvatar

/**
 * Application initialization configuration
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see IUserService
 * @see IUserInfoService
 * @see PasswordEncoder
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
                StrUtil.getRandomPassword(10)
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
                avatar =
                    GitHubAvatar.newAvatarBuilder().build().createAsBase64((Long.MIN_VALUE..Long.MAX_VALUE).random())
                email = AdminProperties.email
            }

            if (userService.save(user) && userInfoService.save(userInfo)) {
                logger.warn("First startup, create administrator - username: admin, password: $rawPassword")
                logger.warn("This information will only be shown once. Please change your password promptly after logging in.")
            }
        }

    }
}
package top.fatweb.api.config

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import jakarta.annotation.PostConstruct
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import top.fatweb.api.entity.permission.User
import top.fatweb.api.service.IUserService
import kotlin.random.Random

@Component
class InitConfig(
    private val userService: IUserService, private val passwordEncoder: PasswordEncoder
) {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @PostConstruct
    fun init() {
        if (!userService.exists(KtQueryWrapper(User()).eq(User::id, 0))) {
            val rawPassword = getRandPassword(10)
            val encodedPassword = passwordEncoder.encode(rawPassword)
            val user = User().apply {
                id = 0
                username = "admin"
                password = encodedPassword
                locking = 0
                enable = 1
            }
            if (userService.save(user)) {
                logger.warn("First startup, create administrator - username: admin, password: $rawPassword")
                logger.warn("This information will only be shown once. Please change your password promptly after logging in.")
            }
        }

    }

    private fun getRandPassword(length: Int): String {
        val characterSet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"

        val random = Random(System.nanoTime())
        val password = StringBuilder()

        for (i in 0 until length) {
            val rIndex = random.nextInt(characterSet.length)
            password.append(characterSet[rIndex])
        }

        return password.toString()
    }
}
package top.fatweb.api

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.test.context.junit.jupiter.SpringExtension
import top.fatweb.api.constant.SecurityConstants
import top.fatweb.api.util.JwtUtil

@ExtendWith(SpringExtension::class)
class FatWebApiApplicationTests {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @Test
    fun removePrefixTest() {
        assertEquals("12312", "Bearer 12312".removePrefix(SecurityConstants.tokenPrefix))
    }

    @Test
    fun jwtTest() {
        val jwt = JwtUtil.createJwt("User")
        assertEquals("User", jwt?.let { JwtUtil.parseJwt(it).subject })
    }

    /*
        @Test
        fun generatePassword() {
            val passwordEncoder = BCryptPasswordEncoder()
            logger.info(passwordEncoder.encode("admin@dev"))
        }
    */
}

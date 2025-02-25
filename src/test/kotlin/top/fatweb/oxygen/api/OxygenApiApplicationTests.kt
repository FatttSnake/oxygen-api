package top.fatweb.oxygen.api

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.test.context.junit.jupiter.SpringExtension
import top.fatweb.oxygen.api.properties.SecurityProperties
import top.fatweb.oxygen.api.util.ByteUtil
import top.fatweb.oxygen.api.util.JwtUtil
import top.fatweb.oxygen.api.util.StrUtil
import java.util.concurrent.TimeUnit


@ExtendWith(SpringExtension::class)
class OxygenApiApplicationTests {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @Test
    fun removePrefixTest() {
        assertEquals("12312", "Bearer 12312".removePrefix(SecurityProperties.tokenPrefix))
    }

    @Test
    fun jwtTest() {
        val jwt = JwtUtil.generateAccessToken("User")
        assertEquals("User", jwt?.let { JwtUtil.parseJwt(it).subject })
    }

    /*
        @Test
        fun generatePassword() {
            val passwordEncoder = BCryptPasswordEncoder()
            logger.info(passwordEncoder.encode("admin@dev"))
        }
    */

    @Test
    fun byteUtilTest() {
        assertEquals("512B", ByteUtil.formatByteSize(512))
        assertEquals("512KiB", ByteUtil.formatByteSize(512 * 1024))
        assertEquals("1.5MiB", ByteUtil.formatByteSize(1 * 1024 * 1024 + 512 * 1024))
    }

    @Test
    fun upperToUnderLetterTest() {
        assertEquals("create_time", StrUtil.upperToUnderLetter("createTime"))
    }

    @Test
    fun underToUpperLetterTest() {
        assertEquals("createTime", StrUtil.underToUpperLetter("create_time"))
    }

    @Test
    fun timeUnitTest() {
        assertEquals(60000, TimeUnit.MINUTES.toMillis(1))
    }
}

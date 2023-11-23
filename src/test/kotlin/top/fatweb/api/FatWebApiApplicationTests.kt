package top.fatweb.api

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.test.context.junit.jupiter.SpringExtension
import top.fatweb.api.properties.SecurityProperties
import top.fatweb.api.util.ByteUtil
import top.fatweb.api.util.JwtUtil
import top.fatweb.api.util.StrUtil
import top.fatweb.avatargenerator.GitHubAvatar
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi


@ExtendWith(SpringExtension::class)
class FatWebApiApplicationTests {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @Test
    fun removePrefixTest() {
        assertEquals("12312", "Bearer 12312".removePrefix(SecurityProperties.tokenPrefix))
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

    @Test
    fun byteUtilTest() {
        assertEquals("512B", ByteUtil.formatByteSize(512))
        assertEquals("512KiB", ByteUtil.formatByteSize(512 * 1024))
        assertEquals("1.5MiB", ByteUtil.formatByteSize(1 * 1024 * 1024 + 512 * 1024))
    }

    @OptIn(ExperimentalEncodingApi::class)
    @Test
    fun name() {
        val avatar = GitHubAvatar.newAvatarBuilder(396, 5).build()
        val bytes = avatar.createAsPngBytes(1232132134543L)
        logger.info(Base64.encode(bytes))
    }

    @Test
    fun upperToUnderLetterTest() {
        assertEquals("create_time", StrUtil.upperToUnderLetter("createTime"))
    }

    @Test
    fun underToUpperLetterTest() {
        assertEquals("createTime", StrUtil.underToUpperLetter("create_time"))
    }
}

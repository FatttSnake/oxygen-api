package top.fatweb.api

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import top.fatweb.api.constant.SecurityConstants

@SpringBootTest
class FatWebApiApplicationTests {

    @Test
    fun contextLoads() {
    }

    @Test
    fun removePrefixTest() {
        assertEquals("12312", "Bearer 12312".removePrefix(SecurityConstants.tokenPrefix))
    }
}

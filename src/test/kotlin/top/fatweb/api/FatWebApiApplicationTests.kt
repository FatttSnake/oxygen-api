package top.fatweb.api

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import top.fatweb.api.constants.SecurityConstants
import java.security.MessageDigest
import java.util.*

@SpringBootTest
class FatWebApiApplicationTests {

    @Test
    fun contextLoads() {
        SecurityConstants.jwtKey
    }

}

package top.fatweb.oxygen.api.util

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class StrUtilTest {
    @Test
    fun upperToUnderLetter() {
        assertEquals("create_time", upperToUnderLetter("createTime"))
    }

    @Test
    fun underToUpperLetter() {
        assertEquals("createTime", underToUpperLetter("create_time"))
    }
}
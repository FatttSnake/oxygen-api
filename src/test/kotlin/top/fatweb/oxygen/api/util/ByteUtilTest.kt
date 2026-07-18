package top.fatweb.oxygen.api.util

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ByteUtilTest {
    @Test
    fun formatByteSize() {
        assertEquals("512B", ByteUtil.formatByteSize(512))
        assertEquals("512KiB", ByteUtil.formatByteSize(512 * 1024))
        assertEquals("1.5MiB", ByteUtil.formatByteSize(1 * 1024 * 1024 + 512 * 1024))
    }

    @Test
    fun sha256HexString() {
        assertEquals(
            "dffd6021bb2bd5b0af676290809ec3a53191dd81c7f70a4b28688a362182986f",
            "Hello, World!".toByteArray().sha256HexString()
        )
    }
}
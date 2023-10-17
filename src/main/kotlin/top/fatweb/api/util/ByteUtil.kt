package top.fatweb.api.util

import kotlin.math.floor

object ByteUtil {
    private const val BASE = 1024

    enum class ByteUnit {
        B, KiB, MiB, GiB, TiB, PiB, EiB, ZiB, YiB
    }

    fun formatByteSize(byteSize: Long): String {
        if (byteSize <= -1) {
            return byteSize.toString()
        }

        var size = byteSize.toDouble()
        if (floor(size / BASE) <= 0) {
            return format(size, ByteUnit.B)
        }

        size /= BASE
        if (floor(size / BASE) <= 0) {
            return format(size, ByteUnit.KiB)
        }

        size /= BASE
        if (floor(size / BASE) <= 0) {
            return format(size, ByteUnit.MiB)
        }

        size /= BASE
        if (floor(size / BASE) <= 0) {
            return format(size, ByteUnit.GiB)
        }

        size /= BASE
        if (floor(size / BASE) <= 0) {
            return format(size, ByteUnit.TiB)
        }

        size /= BASE
        if (floor(size / BASE) <= 0) {
            return format(size, ByteUnit.PiB)
        }

        size /= BASE
        if (floor(size / BASE) <= 0) {
            return format(size, ByteUnit.EiB)
        }

        size /= BASE
        if (floor(size / BASE) <= 0) {
            return format(size, ByteUnit.ZiB)
        }

        size /= BASE
        return format(size, ByteUnit.YiB)
    }

    fun format(size: Double, unit: ByteUnit): String {
        val precision = if (size * 1000 % 10 > 0) {
            3
        } else if (size * 100 % 10 > 0) {
            2
        } else if (size * 10 % 10 > 0) {
            1
        } else {
            0
        }

        val formatStr = "%.${precision}f"

        return String.format(formatStr, size) + unit.name
    }
}
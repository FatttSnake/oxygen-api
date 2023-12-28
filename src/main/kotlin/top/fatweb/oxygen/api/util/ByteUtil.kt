package top.fatweb.oxygen.api.util

import kotlin.math.floor

/**
 * Byte util
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
object ByteUtil {
    private const val BASE = 1024

    /**
     * Byte unit
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    enum class ByteUnit {
        /**
         * Byte
         *
         * @author FatttSnake, fatttsnake@gmail.com
         * @since 1.0.0
         */
        B,

        /**
         * Kibibyte
         *
         * @author FatttSnake, fatttsnake@gmail.com
         * @since 1.0.0
         */
        KiB,

        /**
         * Mebibyte
         *
         * @author FatttSnake, fatttsnake@gmail.com
         * @since 1.0.0
         */
        MiB,

        /**
         * Gibibyte
         *
         * @author FatttSnake, fatttsnake@gmail.com
         * @since 1.0.0
         */
        GiB,

        /**
         * Tebibyte
         *
         * @author FatttSnake, fatttsnake@gmail.com
         * @since 1.0.0
         */
        TiB,

        /**
         * Pebibyte
         *
         * @author FatttSnake, fatttsnake@gmail.com
         * @since 1.0.0
         */
        PiB,

        /**
         * Exbibyte
         *
         * @author FatttSnake, fatttsnake@gmail.com
         * @since 1.0.0
         */
        EiB,

        /**
         * Zebibyte
         *
         * @author FatttSnake, fatttsnake@gmail.com
         * @since 1.0.0
         */
        ZiB,

        /**
         * Yobibyte
         *
         * @author FatttSnake, fatttsnake@gmail.com
         * @since 1.0.0
         */
        YiB
    }

    /**
     * Format byte size
     *
     * @param byteSize ByteSize
     * @return Result
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
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

    private fun format(size: Double, unit: ByteUnit): String {
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
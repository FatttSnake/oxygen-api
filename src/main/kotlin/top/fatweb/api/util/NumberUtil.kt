package top.fatweb.api.util

object NumberUtil {
    fun getRandomInt(start: Int = Int.MIN_VALUE, end: Int = Int.MAX_VALUE): Int {
        return (start..end).random()
    }

    fun getRandomLong(start: Long = Long.MIN_VALUE, end: Long = Long.MAX_VALUE): Long {
        return (start..end).random()
    }
}
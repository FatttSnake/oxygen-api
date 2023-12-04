package top.fatweb.api.util

import java.security.MessageDigest
import java.util.regex.Pattern

/**
 * String util
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
object StrUtil {
    fun upperToUnderLetter(str: String?): String {
        str ?: let { return "" }

        val matcher = Pattern.compile("([A-Z])").matcher(str)
        val stringBuilder = StringBuilder()

        while (matcher.find()) {
            matcher.appendReplacement(stringBuilder, "_" + matcher.group().lowercase())
        }

        matcher.appendTail(stringBuilder)

        return stringBuilder.toString()
    }

    fun underToUpperLetter(str: String?): String {
        str ?: let { return "" }

        val matcher = Pattern.compile("(_)([a-z])").matcher(str)
        val stringBuilder = StringBuilder()

        while (matcher.find()) {
            matcher.appendReplacement(stringBuilder, matcher.group().replace("_", "").uppercase())
        }

        matcher.appendTail(stringBuilder)

        return stringBuilder.toString()
    }

     fun getRandomPassword(length: Int): String {
        val characterSet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val password = StringBuilder()

        for (i in 0 until length) {
            password.append(characterSet.random())
        }

        return password.toString()
    }

    fun md5(content: String): String {
        val hash = MessageDigest.getInstance("MD5").digest(content.toByteArray())
        val hex = StringBuilder(hash.size * 2)
        for (b in hash) {
            var str = Integer.toHexString(b.toInt())
            if (b < 0x10) {
                str = "0$str"
            }
            hex.append(str.substring(str.length -2))
        }
        return hex.toString()
    }
}
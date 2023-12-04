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
    
    /**
     * Convert CamelCase string to underscore-delimited string
     *
     * @param str CamelCase string
     * @return Underscore-delimited string
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
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

    /**
     * Convert underscore-delimited string to CamelCase string
     *
     * @param str Underscore-delimited string
     * @return CamelCase string
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
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

    /**
     * Get random password
     *
     * @param length Length of password
     * @return Random password
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
     fun getRandomPassword(length: Int): String {
        val characterSet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val password = StringBuilder()

        for (i in 0 until length) {
            password.append(characterSet.random())
        }

        return password.toString()
    }

    /**
     * Get MD5 of a string
     *
     * @param content Content
     * @return MD5 of content
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
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
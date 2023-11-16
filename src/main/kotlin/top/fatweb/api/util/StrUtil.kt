package top.fatweb.api.util

import java.util.regex.Pattern

/**
 * String util
 *
 * @author FatttSnake
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
}
package top.fatweb.api.param.system

import com.baomidou.mybatisplus.annotation.EnumValue
import com.fasterxml.jackson.annotation.JsonValue

/**
 * Get online information parameters
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
data class OnlineInfoGetParam(
    val scope: Scope = Scope.WEAK
) {
    enum class Scope(@field:EnumValue @field:JsonValue val code: String) {
        DAY("DAY"),

        WEAK("WEAK"),

        MONTH("MONTH"),

        QUARTER("QUARTER"),

        YEAR("YEAR"),

        TWO_YEARS("TWO_YEARS"),

        THREE_YEARS("THREE_YEARS"),

        FIVE_YEARS("FIVE_YEARS"),

        ALL("ALL")
    }
}

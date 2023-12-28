package top.fatweb.oxygen.api.param.system

import com.baomidou.mybatisplus.annotation.EnumValue
import com.fasterxml.jackson.annotation.JsonValue
import io.swagger.v3.oas.annotations.media.Schema

/**
 * Get active information parameters
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
data class ActiveInfoGetParam(
    /**
     * Scope
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(
        description = "范围",
        allowableValues = ["WEAK", "MONTH", "QUARTER", "YEAR", "TWO_YEARS", "THREE_YEARS", "FIVE_YEARS", "ALL"],
        defaultValue = "WEAK",
        example = "WEAK"
    )
    val scope: Scope = Scope.WEAK
) {
    enum class Scope(@field:EnumValue @field:JsonValue val code: String) {
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

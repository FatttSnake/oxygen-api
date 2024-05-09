package top.fatweb.oxygen.api.entity.tool

import com.baomidou.mybatisplus.annotation.EnumValue
import com.fasterxml.jackson.annotation.JsonValue

/**
 * Platform enum
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
enum class Platform(@field:EnumValue @field:JsonValue val code: String) {
    WEB("WEB"), DESKTOP("DESKTOP"), ANDROID("ANDROID")
}
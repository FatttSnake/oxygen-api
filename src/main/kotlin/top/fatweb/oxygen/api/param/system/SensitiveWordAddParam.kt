package top.fatweb.oxygen.api.param.system

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import top.fatweb.oxygen.api.entity.system.SensitiveWord

/**
 * Add sensitive word settings parameters
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Schema(defaultValue = "敏感词添加请求参数")
data class SensitiveWordAddParam(
    /**
     * Word
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "词", required = true)
    @field:NotBlank(message = "Word can not be blank")
    val word: String?,

    /**
     * Use for
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see SensitiveWord.Use
     */
    @Schema(description = "用于", allowableValues = ["USERNAME"])
    val useFor: Set<SensitiveWord.Use> = emptySet(),

    /**
     * Enable
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Schema(description = "启用", allowableValues = ["true", "false"], defaultValue = "true")
    val enable: Boolean = true
)

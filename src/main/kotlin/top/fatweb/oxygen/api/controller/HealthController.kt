package top.fatweb.oxygen.api.controller

import org.springframework.web.bind.annotation.GetMapping
import top.fatweb.oxygen.api.annotation.HiddenController
import java.time.LocalDateTime

/**
 * Health check controller
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.3.0
 */
@HiddenController(["/health"])
class HealthController {
    @GetMapping
    fun health(): Map<String, Any> = mapOf(
        "status" to "UP",
        "timestamp" to LocalDateTime.now().toString()
    )
}

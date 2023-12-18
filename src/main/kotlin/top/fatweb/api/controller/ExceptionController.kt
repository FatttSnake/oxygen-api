package top.fatweb.api.controller

import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.RequestMapping
import top.fatweb.api.annotation.HiddenController

/**
 * Exception controller
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@HiddenController(["/error"])
class ExceptionController {
    @RequestMapping("/thrown")
    fun thrown(request: HttpServletRequest) {
        throw request.getAttribute("filter.error") as RuntimeException
    }
}
package top.fatweb.api.controller

import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/error")
class ExceptionController {
    @RequestMapping("/thrown")
    fun thrown(request: HttpServletRequest) {
        throw request.getAttribute("filter.error") as RuntimeException
    }
}
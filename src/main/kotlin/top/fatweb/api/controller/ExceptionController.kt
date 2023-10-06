package top.fatweb.api.controller

import io.swagger.v3.oas.annotations.Hidden
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Hidden
@RestController
@RequestMapping("/error")
class ExceptionController {
    @RequestMapping("/thrown")
    fun thrown(request: HttpServletRequest) {
        throw request.getAttribute("filter.error") as RuntimeException
    }
}
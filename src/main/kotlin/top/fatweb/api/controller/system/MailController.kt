package top.fatweb.api.controller.system

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "邮件接口", description = "邮件发送接口")
@RequestMapping("/system/mail")
@RestController
class MailController {

}
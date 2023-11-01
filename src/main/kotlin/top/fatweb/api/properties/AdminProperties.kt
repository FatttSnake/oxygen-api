package top.fatweb.api.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("app.admin")
object AdminProperties {
    var username = "admin"
    var password: String? = null
    var nickname = "Administrator"
    var email = "admin@fatweb.top"
}
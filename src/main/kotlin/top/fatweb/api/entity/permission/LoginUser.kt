package top.fatweb.api.entity.permission

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class LoginUser() : UserDetails {
    lateinit var user: User

    @JsonIgnore
    private var authorities: List<GrantedAuthority>? = null

    constructor(user: User) : this() {
        this.user = user
    }

    @JsonIgnore
    override fun getAuthorities(): List<GrantedAuthority> {
        authorities?.let { return it }
        authorities = emptyList()

        return authorities as List<GrantedAuthority>
    }

    override fun getPassword(): String? = user.password

    override fun getUsername(): String? = user.username

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = user.enable == 1
}
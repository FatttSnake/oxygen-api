package top.fatweb.api.entity.permission

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonTypeInfo
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
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

    @JsonIgnore
    override fun getPassword(): String? = user.password

    @JsonIgnore
    override fun getUsername(): String? = user.username

    @JsonIgnore
    override fun isAccountNonExpired(): Boolean = true

    @JsonIgnore
    override fun isAccountNonLocked(): Boolean = true

    @JsonIgnore
    override fun isCredentialsNonExpired(): Boolean = true

    @JsonIgnore
    override fun isEnabled(): Boolean = user.enable == 1
}
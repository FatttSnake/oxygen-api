package top.fatweb.oxygen.api.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import top.fatweb.oxygen.api.filter.JwtAuthenticationTokenFilter
import top.fatweb.oxygen.api.handler.JwtAccessDeniedHandler
import top.fatweb.oxygen.api.handler.JwtAuthenticationEntryPointHandler
import top.fatweb.oxygen.api.util.CookieWithHeaderCsrfTokenRepository

/**
 * Spring Security configuration
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see JwtAuthenticationTokenFilter
 * @see JwtAuthenticationEntryPointHandler
 * @see JwtAccessDeniedHandler
 */
@Configuration
@EnableMethodSecurity
class SecurityConfig(
    private val jwtAuthenticationTokenFilter: JwtAuthenticationTokenFilter,
    private val authenticationEntryPointHandler: JwtAuthenticationEntryPointHandler,
    private val accessDeniedHandler: JwtAccessDeniedHandler
) {
    @Bean
    fun passwordEncoder(): PasswordEncoder = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8()

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager =
        authenticationConfiguration.authenticationManager

    @Bean
    fun corsConfigurationSource(): UrlBasedCorsConfigurationSource {
        return UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration("/**", CorsConfiguration().apply {
                allowedOriginPatterns = listOf("*")
                allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                allowedHeaders = listOf("Authorization", "Content-Type", "X-XSRF-TOKEN")
                allowCredentials = true
                maxAge = 3600L
                exposedHeaders = listOf("X-XSRF-TOKEN")
            })
        }
    }

    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain = httpSecurity
        .cors {
            it.configurationSource(
                corsConfigurationSource()
            )
        }

        .csrf {
            it.csrfTokenRepository(CookieWithHeaderCsrfTokenRepository())
            it.csrfTokenRequestHandler(CsrfTokenRequestAttributeHandler())
            it.requireCsrfProtectionMatcher { request ->
                PathPatternRequestMatcher.withDefaults().matcher(HttpMethod.POST, "/token").matches(request) &&
                        request.getParameter("refreshToken").isNullOrBlank()
            }
        }

        .authorizeHttpRequests {
            it
                .requestMatchers(
                    "/error/thrown",
                    "/doc.html",
                    "/swagger-ui/**",
                    "/webjars/**",
                    "/v3/**",
                    "/swagger-ui.html",
                    "/favicon.ico",
                    "/login",
                    "/register",
                    "/forget",
                    "/retrieve"
                ).anonymous()
                .requestMatchers(
                    "/token",
                    "/tool/dist/**",
                    "/tool/base/**",
                    "/tool/store",
                    "/tool/store/*",
                    "/system/user/info/*"
                ).permitAll()
                .anyRequest().authenticated()
        }

        .logout {
            it.disable()
        }

        .exceptionHandling {
            it.authenticationEntryPoint(
                authenticationEntryPointHandler
            )
            it.accessDeniedHandler(
                accessDeniedHandler
            )
        }

        .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter::class.java).build()
}

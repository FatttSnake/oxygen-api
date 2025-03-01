package top.fatweb.oxygen.api.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.csrf.CookieCsrfTokenRepository
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import top.fatweb.oxygen.api.filter.JwtAuthenticationTokenFilter
import top.fatweb.oxygen.api.handler.JwtAccessDeniedHandler
import top.fatweb.oxygen.api.handler.JwtAuthenticationEntryPointHandler

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
    fun passwordEncoder() = BCryptPasswordEncoder()

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
            it.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            it.csrfTokenRequestHandler(CsrfTokenRequestAttributeHandler())
            it.ignoringRequestMatchers("/error/thrown")
        }

        .authorizeHttpRequests {
            it
                // Allow anonymous access
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
                    "/tool/detail/**",
                    "/tool/store",
                    "/tool/store/*",
                    "/system/user/info/*"
                ).permitAll()
                // Authentication required
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
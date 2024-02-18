package top.fatweb.oxygen.api.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.*
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
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
        val corsConfiguration = CorsConfiguration()
        corsConfiguration.allowedMethods = listOf("*")
        corsConfiguration.allowedHeaders = listOf("*")
        corsConfiguration.maxAge = 3600L
        corsConfiguration.allowedOrigins = listOf("*")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", corsConfiguration)

        return source
    }

    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity): SecurityFilterChain = httpSecurity
        //  Disable CSRF
        .csrf { csrfConfigurer: CsrfConfigurer<HttpSecurity> -> csrfConfigurer.disable() }
        // Do not get SecurityContent by Session
        .sessionManagement { sessionManagementConfigurer: SessionManagementConfigurer<HttpSecurity?> ->
            sessionManagementConfigurer.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS
            )
        }
        .authorizeHttpRequests { authorizeHttpRequests: AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry ->
            authorizeHttpRequests
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
                .requestMatchers("/tool/detail/**", "/tool/store").permitAll()
                // Authentication required
                .anyRequest().authenticated()
        }

        .logout { logoutConfigurer: LogoutConfigurer<HttpSecurity> -> logoutConfigurer.disable() }

        .exceptionHandling { exceptionHandlingConfigurer: ExceptionHandlingConfigurer<HttpSecurity?> ->
            exceptionHandlingConfigurer.authenticationEntryPoint(
                authenticationEntryPointHandler
            )
            exceptionHandlingConfigurer.accessDeniedHandler(
                accessDeniedHandler
            )
        }

        .cors { cors: CorsConfigurer<HttpSecurity?> ->
            cors.configurationSource(
                corsConfigurationSource()
            )
        }

        .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter::class.java).build()
}
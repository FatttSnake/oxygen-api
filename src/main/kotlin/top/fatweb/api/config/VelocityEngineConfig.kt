package top.fatweb.api.config

import org.apache.velocity.app.VelocityEngine
import org.apache.velocity.runtime.RuntimeConstants
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Velocity engine configuration
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Configuration
class VelocityEngineConfig {
    @Bean
    fun velocityEngine() = VelocityEngine().apply {
        setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath")
        setProperty("classpath.resource.loader.class", ClasspathResourceLoader::class.java.name)
        init()
    }
}
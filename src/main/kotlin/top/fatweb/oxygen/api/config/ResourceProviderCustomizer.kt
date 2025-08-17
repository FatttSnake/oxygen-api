package top.fatweb.oxygen.api.config

import org.flywaydb.core.api.configuration.FluentConfiguration
import org.springframework.stereotype.Component

/**
 * A Flyway customizer which gets replaced with
 * [NativeImageResourceProviderCustomizer] when running in a native image.
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.1.0
 */
@Component
class ResourceProviderCustomizer {
    fun customize(configuration: FluentConfiguration) {
    }
}

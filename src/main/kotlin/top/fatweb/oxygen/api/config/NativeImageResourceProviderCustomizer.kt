package top.fatweb.oxygen.api.config

import org.flywaydb.core.api.configuration.FluentConfiguration
import org.flywaydb.core.api.migration.JavaMigration
import org.flywaydb.core.internal.scanner.LocationScannerCache
import org.flywaydb.core.internal.scanner.ResourceNameCache
import org.flywaydb.core.internal.scanner.Scanner
import org.springframework.stereotype.Component

/**
 * Registers [NativeImageResourceProvider] as a Flyway
 * [org.flywaydb.core.api.ResourceProvider].
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.1.0
 */
@Component
class NativeImageResourceProviderCustomizer : ResourceProviderCustomizer() {
    override fun customize(configuration: FluentConfiguration) {
        if (configuration.resourceProvider == null) {
            val scanner = Scanner(
                JavaMigration::class.java,
                listOf(*configuration.locations), configuration.classLoader,
                configuration.encoding, configuration.isDetectEncoding, false, ResourceNameCache(),
                LocationScannerCache(), configuration.isFailOnMissingLocations
            )
            val resourceProvider = NativeImageResourceProvider(
                scanner,
                configuration.classLoader, mutableListOf(*configuration.locations),
                configuration.encoding, configuration.isFailOnMissingLocations
            )
            configuration.resourceProvider(resourceProvider)
        }
    }
}

package top.fatweb.oxygen.api.config

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource
import jakarta.annotation.PostConstruct
import org.flywaydb.core.api.configuration.FluentConfiguration
import org.springframework.beans.factory.ObjectProvider
import org.springframework.boot.autoconfigure.flyway.FlywayConfigurationCustomizer
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import org.springframework.core.io.ResourceLoader
import top.fatweb.oxygen.api.properties.FlywayProperties
import javax.sql.DataSource

/**
 * Flyway configuration
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@DependsOn("flywayProperties")
@Configuration
class FlywayConfig(
    private val dataSource: DataSource,
    private val resourceLoader: ResourceLoader,
    private val fluentConfigurationCustomizers: ObjectProvider<FlywayConfigurationCustomizer>,
    private val resourceProviderCustomizer: ResourceProviderCustomizer
) {
    @PostConstruct
    fun migrateOrder() {
        val ds = dataSource as DynamicRoutingDataSource
        ds.dataSources.forEach { (k: String, v: DataSource?) ->
            val configuration = FluentConfiguration(resourceLoader.classLoader)
                .dataSource(v)
                .locations(*FlywayProperties.locations.map { "$it/$k" }.toTypedArray())
                .baselineOnMigrate(FlywayProperties.baselineOnMigrate)
                .table(FlywayProperties.table)
                .outOfOrder(FlywayProperties.outOfOrder)
                .validateOnMigrate(FlywayProperties.validateOnMigrate)
                .encoding(FlywayProperties.encoding)
                .sqlMigrationPrefix(FlywayProperties.sqlMigrationPrefix)
                .sqlMigrationSeparator(FlywayProperties.sqlMigrationSeparator)
                .sqlMigrationSuffixes(*FlywayProperties.sqlMigrationSuffixes.toTypedArray())
                .baselineVersion(FlywayProperties.baselineVersion)
            fluentConfigurationCustomizers.orderedStream().forEach { it.customize(configuration) }
            resourceProviderCustomizer.customize(configuration)
            configuration.load().migrate()
        }
    }
}

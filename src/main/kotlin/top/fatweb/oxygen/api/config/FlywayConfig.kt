package top.fatweb.oxygen.api.config

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource
import jakarta.annotation.PostConstruct
import org.flywaydb.core.Flyway
import org.springframework.context.annotation.Configuration
import top.fatweb.oxygen.api.properties.FlywayProperties
import javax.sql.DataSource

/**
 * Flyway configuration
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see FlywayProperties
 * @see DataSource
 */
@Configuration
class FlywayConfig(
    private val flywayProperties: FlywayProperties,
    private val dataSource: DataSource
) {
    @PostConstruct
    fun migrateOrder() {
        val ds = dataSource as DynamicRoutingDataSource
        ds.dataSources.forEach { (k: String, v: DataSource?) ->
            val flyway = Flyway.configure()
                .dataSource(v)
                .locations(*flywayProperties.locations.map { "$it/$k" }.toTypedArray())
                .baselineOnMigrate(flywayProperties.baselineOnMigrate)
                .table(flywayProperties.table)
                .outOfOrder(flywayProperties.outOfOrder)
                .validateOnMigrate(flywayProperties.validateOnMigrate)
                .encoding(flywayProperties.encoding)
                .sqlMigrationPrefix(flywayProperties.sqlMigrationPrefix)
                .sqlMigrationSeparator(flywayProperties.sqlMigrationSeparator)
                .sqlMigrationSuffixes(*flywayProperties.sqlMigrationSuffixes.toTypedArray())
                .baselineVersion(flywayProperties.baselineVersion)
                .load()
            flyway.migrate()
        }

    }
}

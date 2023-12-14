package top.fatweb.api.config

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource
import jakarta.annotation.PostConstruct
import org.flywaydb.core.Flyway
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import top.fatweb.api.properties.FlywayProperties
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
    private val dataSource: DataSource
) {
    @PostConstruct
    fun migrateOrder() {
        val ds = dataSource as DynamicRoutingDataSource
        ds.dataSources.forEach { (k: String, v: DataSource?) ->
            val flyway = Flyway.configure()
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
                .load()
            flyway.migrate()
        }

    }
}
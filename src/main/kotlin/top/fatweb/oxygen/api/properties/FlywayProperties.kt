package top.fatweb.oxygen.api.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 * Flyway properties
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
@Component
@ConfigurationProperties("spring.flyway")
object FlywayProperties {
    /**
     * Locations of migrations scripts. Can contain the special "{vendor}" placeholder to
     * use vendor-specific locations.
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    var locations = listOf("classpath:db/migration")

    /**
     * Name of the schema history table that will be used by Flyway.
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    var table = "flyway_schema_history"

    /**
     * Whether to allow migrations to be run out of order.
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    var outOfOrder = false

    /**
     * Whether to automatically call validate when performing a migration.
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    var validateOnMigrate = true

    /**
     * Encoding of SQL migrations.
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    var encoding = Charsets.UTF_8

    /**
     * File name prefix for SQL migrations.
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    var sqlMigrationPrefix = "V"

    /**
     * File name separator for SQL migrations.
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    var sqlMigrationSeparator = "__"

    /**
     * File name suffix for SQL migrations.
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    var sqlMigrationSuffixes = listOf(".sql")

    /**
     * Whether to automatically call baseline when migrating a non-empty schema.
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    var baselineOnMigrate = true

    /**
     * Version to tag an existing schema with when executing baseline.
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    var baselineVersion = "0"
}
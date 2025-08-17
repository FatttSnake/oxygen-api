package top.fatweb.oxygen.api.config

import org.flywaydb.core.api.FlywayException
import org.flywaydb.core.api.Location
import org.flywaydb.core.api.ResourceProvider
import org.flywaydb.core.api.resource.LoadableResource
import org.flywaydb.core.internal.resource.classpath.ClassPathResource
import org.flywaydb.core.internal.scanner.Scanner
import org.flywaydb.core.internal.util.StringUtils
import org.springframework.core.NativeDetector
import org.springframework.core.io.Resource
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import java.io.IOException
import java.io.UncheckedIOException
import java.nio.charset.Charset
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock
import java.util.function.Predicate

/**
 * A Flyway [ResourceProvider] which supports GraalVM native-image.
 *
 * It delegates work to Flyways [Scanner], and additionally uses
 * [PathMatchingResourcePatternResolver] to find migration files in a native image.
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.1.0
 */
class NativeImageResourceProvider(
    private val scanner: Scanner<*>,
    private val classLoader: ClassLoader,
    private val locations: MutableCollection<Location>,
    private val encoding: Charset,
    private val failOnMissingLocations: Boolean
) : ResourceProvider {
    private val locatedResources: MutableList<LocatedResource> = ArrayList<LocatedResource>()

    private val lock: Lock = ReentrantLock()

    private var initialized = false

    override fun getResource(name: String): LoadableResource? {
        if (!NativeDetector.inNativeImage()) {
            return this.scanner.getResource(name)
        }
        val resource = this.scanner.getResource(name)
        if (resource != null) {
            return resource
        }
        if (this.classLoader.getResource(name) == null) {
            return null
        }
        return ClassPathResource(null, name, this.classLoader, this.encoding)
    }

    override fun getResources(prefix: String, suffixes: Array<String>): MutableCollection<LoadableResource> {
        if (!NativeDetector.inNativeImage()) {
            return this.scanner.getResources(prefix, *suffixes)
        }
        ensureInitialized()
        val matchesPrefixAndSuffixes: Predicate<LocatedResource> = Predicate { locatedResource: LocatedResource ->
            StringUtils
                .startsAndEndsWith(locatedResource.resource.filename, prefix, *suffixes)
        }
        val result: MutableList<LoadableResource> = ArrayList()
        result.addAll(this.scanner.getResources(prefix, *suffixes))
        this.locatedResources.stream()
            .filter(matchesPrefixAndSuffixes)
            .map { locatedResource: LocatedResource -> this.asClassPathResource(locatedResource) }
            .forEach { e: ClassPathResource -> result.add(e) }
        return result
    }

    private fun asClassPathResource(locatedResource: LocatedResource): ClassPathResource {
        val location = locatedResource.location
        val fileNameWithAbsolutePath = location.path + "/" + locatedResource.resource.filename
        return ClassPathResource(location, fileNameWithAbsolutePath, this.classLoader, this.encoding)
    }

    private fun ensureInitialized() {
        this.lock.lock()
        try {
            if (!this.initialized) {
                initialize()
                this.initialized = true
            }
        } finally {
            this.lock.unlock()
        }
    }

    private fun initialize() {
        val resolver = PathMatchingResourcePatternResolver()
        for (location in this.locations) {
            if (!location.isClassPath) {
                continue
            }
            val root = resolver.getResource(location.descriptor)
            if (!root.exists()) {
                if (this.failOnMissingLocations) {
                    throw FlywayException("Location " + location.descriptor + " doesn't exist")
                }
                continue
            }
            val resources = getResources(resolver, location, root)
            for (resource in resources) {
                this.locatedResources.add(LocatedResource(resource, location))
            }
        }
    }

    private fun getResources(
        resolver: PathMatchingResourcePatternResolver,
        location: Location,
        root: Resource
    ): Array<Resource> {
        try {
            return resolver.getResources(root.uri.toString() + "/*")
        } catch (ex: IOException) {
            throw UncheckedIOException("Failed to list resources for " + location.descriptor, ex)
        }
    }

    private data class LocatedResource(val resource: Resource, val location: Location)
}

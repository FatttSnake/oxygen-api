package top.fatweb.api.util

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.mvc.condition.RequestCondition
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
import top.fatweb.api.annotation.ApiVersion
import java.lang.reflect.Method

/**
 * Api response mapping handler mapping
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
class ApiResponseMappingHandlerMapping : RequestMappingHandlerMapping() {
    private val versionFlag = "{apiVersion}"

    private fun createCondition(clazz: Class<*>): RequestCondition<ApiVersionCondition>? {
        val classRequestMapping = clazz.getAnnotation(RequestMapping::class.java)
        classRequestMapping ?: let { return null }
        val mappingUrlBuilder = StringBuilder()
        if (classRequestMapping.value.isNotEmpty()) {
            mappingUrlBuilder.append(classRequestMapping.value[0])
        }
        val mappingUrl = mappingUrlBuilder.toString()
        if (!mappingUrl.contains(versionFlag)) {
            return null
        }
        val apiVersion = clazz.getAnnotation(ApiVersion::class.java)

        return if (apiVersion == null) ApiVersionCondition(1) else ApiVersionCondition(apiVersion.version)
    }

    override fun getCustomMethodCondition(method: Method): RequestCondition<*>? = createCondition(method.javaClass)

    override fun getCustomTypeCondition(handlerType: Class<*>): RequestCondition<*>? = createCondition(handlerType)
}
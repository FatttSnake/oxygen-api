package top.fatweb.api.util

import org.springframework.web.servlet.mvc.condition.RequestCondition
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
import top.fatweb.api.annotation.ApiController
import java.lang.reflect.Method

/**
 * Api response mapping handler mapping
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 */
class ApiResponseMappingHandlerMapping : RequestMappingHandlerMapping() {

    private fun createCondition(clazz: Class<*>): RequestCondition<ApiVersionCondition>? {
        val apiController = clazz.getAnnotation(ApiController::class.java)
        apiController ?: let { return null }

        return ApiVersionCondition(apiController.version)
    }

    override fun getCustomMethodCondition(method: Method): RequestCondition<*>? = createCondition(method.javaClass)

    override fun getCustomTypeCondition(handlerType: Class<*>): RequestCondition<*>? = createCondition(handlerType)
}
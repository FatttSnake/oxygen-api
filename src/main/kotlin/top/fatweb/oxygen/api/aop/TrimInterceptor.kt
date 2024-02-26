package top.fatweb.oxygen.api.aop

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import top.fatweb.oxygen.api.annotation.Trim
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.*
import kotlin.reflect.jvm.isAccessible

@Component
@Aspect
class TrimInterceptor : HandlerInterceptor {
    @Pointcut("@annotation(top.fatweb.oxygen.api.annotation.Trim)")
    fun trimPointcut() {
    }

    @Before("trimPointcut()")
    fun doBefore(joinPoint: JoinPoint): Any {
        val args = joinPoint.args

        args?.forEachIndexed { index, any ->
            if (args[index]::class.hasAnnotation<Trim>()) args[index] = trim(any)
        }

        return args
    }

    private fun trim(any: Any?): Any? {
        any ?: return null

        when (any) {
            is Boolean, Short, Int, Long, Float, Double -> {
                return any
            }

            is String -> {
                return any.trim()
            }

            else -> {
                val members = any::class.declaredMemberProperties
                if (members.isEmpty()) {
                    return any
                }
                members.forEach {
                    if (!it.returnType.isSupertypeOf(String::class.starProjectedType)
                        || it !is KMutableProperty<*>
                        || !it.hasAnnotation<Trim>()
                    ) {
                        return@forEach
                    }
                    it.isAccessible = true
                    if (it.call(any) != null) {
                        it.setter.call(any, (it.call(any) as String).trim())
                    }
                }
                return any
            }
        }
    }
}
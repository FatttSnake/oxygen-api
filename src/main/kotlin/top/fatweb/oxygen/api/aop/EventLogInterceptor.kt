package top.fatweb.oxygen.api.aop

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.stereotype.Component
import top.fatweb.oxygen.api.annotation.EventLogRecord
import top.fatweb.oxygen.api.service.system.IEventLogService
import top.fatweb.oxygen.api.util.WebUtil
import top.fatweb.oxygen.api.vo.permission.LoginVo
import top.fatweb.oxygen.api.vo.permission.RegisterVo

/**
 * Event log record interceptor
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see IEventLogService
 */
@Aspect
@Component
class EventLogInterceptor(
    private val eventLogService: IEventLogService
) {
    /**
     * Event log record pointcut
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Pointcut("@annotation(top.fatweb.oxygen.api.annotation.EventLogRecord)")
    fun eventLogPointcut() {
    }

    /**
     * Do after event log record pointcut
     *
     * @param joinPoint Join point
     * @param retValue Return value
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     * @see JoinPoint
     */
    @AfterReturning(value = "eventLogPointcut()", returning = "retValue")
    fun doAfter(joinPoint: JoinPoint, retValue: Any?) {
        val annotation = (joinPoint.signature as MethodSignature).method.getAnnotation(EventLogRecord::class.java)

        val userId = WebUtil.getLoginUserId() ?: when (retValue) {
            is LoginVo -> retValue.userId!!
            is RegisterVo -> retValue.userId!!
            else -> -1
        }

        eventLogService.saveEvent(annotation, userId)
    }
}
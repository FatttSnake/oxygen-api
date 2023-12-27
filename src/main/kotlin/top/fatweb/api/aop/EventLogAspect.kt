package top.fatweb.api.aop

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.stereotype.Component
import top.fatweb.api.annotation.EventLogRecord
import top.fatweb.api.service.system.IEventLogService
import top.fatweb.api.util.WebUtil
import top.fatweb.api.vo.permission.LoginVo
import top.fatweb.api.vo.permission.RegisterVo

/**
 * Event log record aspect
 *
 * @author FatttSnake, fatttsnake@gmail.com
 * @since 1.0.0
 * @see IEventLogService
 */
@Aspect
@Component
class EventLogAspect(
    private val eventLogService: IEventLogService
) {
    /**
     * Event log record pointcut
     *
     * @author FatttSnake, fatttsnake@gmail.com
     * @since 1.0.0
     */
    @Pointcut("@annotation(top.fatweb.api.annotation.EventLogRecord)")
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
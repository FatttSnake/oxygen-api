package top.fatweb.api.aop

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import top.fatweb.api.annotation.EventLogRecord
import top.fatweb.api.entity.system.EventLog
import top.fatweb.api.service.system.IEventLogService
import top.fatweb.api.util.WebUtil

@Aspect
@Component
class EventLogAspect(
    private val eventLogService: IEventLogService
) {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @Pointcut("@annotation(top.fatweb.api.annotation.EventLogRecord)")
    fun eventLogPointcut() {}

    @AfterReturning("eventLogPointcut()")
    fun doAfter(joinPoint: JoinPoint) {
        val annotation = (joinPoint.signature as MethodSignature).method.getAnnotation(EventLogRecord::class.java)

        try {
            eventLogService.save(EventLog().apply {
                this.event = annotation.event
                operateUserId = WebUtil.getLoginUserId() ?: -1
            })
        } catch (e: Exception) {
            logger.error("Cannot record event!!!", e)
        }
    }
}
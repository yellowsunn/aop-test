package com.yellowsunn.aop.order.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Aspect
class AspectV2 {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    @Pointcut("execution(* com.yellowsunn.aop.order..*(..))")
    private fun allOrder() {
    }

    @Around("allOrder()")
    fun doLog(joinPoint: ProceedingJoinPoint): Any? {
        log.info("[log] {}", joinPoint.signature)
        return joinPoint.proceed()
    }
}
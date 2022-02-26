package com.yellowsunn.aop.order.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Aspect
class AspectV1 {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    @Around("execution (* com.yellowsunn.aop.order..*(..))")
    fun doLog(joinPoint: ProceedingJoinPoint): Any? {
        log.info("[log] {}", joinPoint.signature)
        return joinPoint.proceed()
    }
}
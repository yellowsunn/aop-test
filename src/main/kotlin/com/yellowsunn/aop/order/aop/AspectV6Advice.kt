package com.yellowsunn.aop.order.aop

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.Signature
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Aspect
class AspectV6Advice {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    @Before("com.yellowsunn.aop.order.aop.Pointcuts.orderAndService()")
    fun doBefore(joinPoint: JoinPoint) {
        log.info("[before] {}", joinPoint.signature)
    }

    @AfterReturning(value = "com.yellowsunn.aop.order.aop.Pointcuts.orderAndService()", returning = "result")
    fun doReturning(joinPoint: JoinPoint, result: Any?) {
        log.info("[return] {} return={}", joinPoint.signature, result)
    }

    @AfterReturning(value = "com.yellowsunn.aop.order.aop.Pointcuts.allOrder()", returning = "result")
    fun doReturning2(joinPoint: JoinPoint, result: String?) {
        log.info("[return] {} return={}", joinPoint.signature, result)
    }

    @AfterThrowing(value = "com.yellowsunn.aop.order.aop.Pointcuts.orderAndService()", throwing = "ex")
    fun doThrowing(joinPoint: JoinPoint, ex: Exception) {
        log.info("[ex] {} message={}", joinPoint.signature, ex.message, ex)
    }

    @After(value = "com.yellowsunn.aop.order.aop.Pointcuts.orderAndService()")
    fun doAfter(joinPoint: JoinPoint) {
        log.info("[after] {}", joinPoint.signature)
    }
}
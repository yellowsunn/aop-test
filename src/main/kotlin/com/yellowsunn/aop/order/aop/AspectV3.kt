package com.yellowsunn.aop.order.aop

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Aspect
class AspectV3 {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    @Pointcut("execution(* com.yellowsunn.aop.order..*(..))")
    private fun allOrder() {
    }

    @Pointcut("execution(* *..*Service.*(..))")
    private fun allService() {
    }

    @Around("allOrder()")
    fun doLog(joinPoint: ProceedingJoinPoint): Any? {
        log.info("[log] {}", joinPoint.signature)
        return joinPoint.proceed()
    }

    @Around("allOrder() && allService()")
    fun doTransaction(joinPoint: ProceedingJoinPoint): Any? {
        return try {
            log.info("[트랜잭션 시작] {}", joinPoint.signature)
            val result = joinPoint.proceed()
            log.info("[트랜잭션 커밋] {}", joinPoint.signature)
            result
        } catch (e: Exception) {
            log.info("[트랜잭션 롤백] {}", joinPoint.signature, e)
            throw e
        } finally {
            log.info("[트랜잭션 릴리즈] {}", joinPoint.signature)
        }
    }
}
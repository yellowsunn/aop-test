package com.yellowsunn.aop.order.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order

class AspectV5Order {

    @Aspect
    @Order(2)
    class LogAspect {
        private val log: Logger = LoggerFactory.getLogger(javaClass)

        @Around("com.yellowsunn.aop.order.aop.Pointcuts.allOrder()")
        fun doLog(joinPoint: ProceedingJoinPoint): Any? {
            log.info("[log] {}", joinPoint.signature)
            return joinPoint.proceed()
        }
    }

    @Aspect
    @Order(1)
    class TxAspect {
        private val log: Logger = LoggerFactory.getLogger(javaClass)

        @Around("com.yellowsunn.aop.order.aop.Pointcuts.orderAndService()")
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
}
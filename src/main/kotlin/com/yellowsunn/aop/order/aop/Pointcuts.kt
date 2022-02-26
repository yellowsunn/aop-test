package com.yellowsunn.aop.order.aop

import org.aspectj.lang.annotation.Pointcut

class Pointcuts {

    @Pointcut("execution(* com.yellowsunn.aop.order..*(..))")
    fun allOrder() {
    }

    @Pointcut("execution(* *..*Service.*(..))")
    fun allService() {
    }

    @Pointcut("allOrder() && allService()")
    fun orderAndService() {
    }
}
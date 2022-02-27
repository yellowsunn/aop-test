package com.yellowsunn.aop

import com.yellowsunn.aop.order.OrderRepository
import com.yellowsunn.aop.order.OrderService
import com.yellowsunn.aop.order.aop.AspectV6Advice
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.aop.support.AopUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@SpringBootTest
@Import(AspectV6Advice::class)
class AopTest {
    @Autowired
    lateinit var orderService: OrderService

    @Autowired
    lateinit var orderRepository: OrderRepository

    val log: Logger = LoggerFactory.getLogger(AopTest::class.java)

    @Test
    fun aopInfo() {
        log.info("isAopProxy, orderService={}", AopUtils.isAopProxy(orderService))
        log.info("isAopProxy, orderRepository={}", AopUtils.isAopProxy(orderRepository))
    }

    @Test
    fun success() {
        orderService.orderItem("itemA")
    }

    @Test
    fun exception() {
        assertThrows<IllegalStateException> { orderService.orderItem("ex") }
    }
}
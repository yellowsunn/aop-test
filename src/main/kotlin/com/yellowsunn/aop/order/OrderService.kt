package com.yellowsunn.aop.order

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class OrderService(private val orderRepository: OrderRepository) {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    fun orderItem(itemId: String) {
        log.info("[OrderService] 실행")
        orderRepository.save(itemId)
    }
}
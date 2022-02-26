package com.yellowsunn.aop.order

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository

@Repository
class OrderRepository {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    fun save(itemId: String): String {
        log.info("[OrderRepository] 실행")

        if (itemId == "ex") {
            throw IllegalStateException("예외 발생!")
        }

        return "ok"
    }
}
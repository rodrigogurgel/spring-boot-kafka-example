package br.com.rodrigogurgel.springbootkafkaexample.controller

import br.com.rodrigogurgel.springbootkafkaexample.domain.Order
import br.com.rodrigogurgel.springbootkafkaexample.producer.OrderProducer
import java.util.UUID
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderController(
    private val orderProducer: OrderProducer
) {
    @GetMapping
    fun createOrder() {
        orderProducer.publish(UUID.randomUUID(), Order(UUID.randomUUID(), UUID.randomUUID(), 1))
    }
}
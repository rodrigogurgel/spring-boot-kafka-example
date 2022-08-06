package br.com.rodrigogurgel.springbootkafkaexample.consumer

import br.com.rodrigogurgel.springbootkafkaexample.avro.Order as avroOrder
import java.util.UUID
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component
import br.com.rodrigogurgel.springbootkafkaexample.domain.Order

@Component
class OrderConsumer {
    companion object {
        private val logger = LoggerFactory.getLogger(OrderConsumer::class.java)
    }

    @KafkaListener(
        id = "",
        topics = ["\${kafka.order.topic-name}"],
        groupId = "order.consumer",
        errorHandler = "genericConsumerErrorHandler"
    )
    fun process(@Payload order: avroOrder) {
        val order = with(order) {
            Order(
                id = UUID.fromString(id),
                productId = UUID.fromString(productId),
                quantity = quantity
            )
        }
        logger.info("{}", order)
    }
}
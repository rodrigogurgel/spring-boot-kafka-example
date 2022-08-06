package br.com.rodrigogurgel.springbootkafkaexample.producer

import br.com.rodrigogurgel.springbootkafkaexample.domain.Order
import java.util.UUID
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service
import br.com.rodrigogurgel.springbootkafkaexample.avro.Order as avroOrder

@Service
class OrderProducer(
    private val kafkaTemplate: KafkaTemplate<String, avroOrder>,
    @Value("\${kafka.order.topic-name}")
    private val topicName: String
) {
    companion object {
        private val logger = LoggerFactory.getLogger(OrderProducer::class.java)
    }

    fun publish(messageId: UUID, payload: Order) {
        val order = with(payload) {
            avroOrder.newBuilder()
                .setId(id.toString())
                .setProductId(productId.toString())
                .setQuantity(quantity)
                .build()
        }

        val message = MessageBuilder.withPayload(order)
            .setHeader("hash", order.hashCode())
            .setHeader(KafkaHeaders.MESSAGE_KEY, messageId.toString())
            .setHeader(KafkaHeaders.TOPIC, topicName)
            .build()

        val future = kafkaTemplate.send(message)
        future.addCallback({
            logger.info("ok {}", it)
        }, {
            logger.error("not ok", it)
        })
    }
}
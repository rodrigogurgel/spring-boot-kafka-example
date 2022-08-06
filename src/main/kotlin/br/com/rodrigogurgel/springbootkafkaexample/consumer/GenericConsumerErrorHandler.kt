package br.com.rodrigogurgel.springbootkafkaexample.consumer

import org.apache.kafka.clients.consumer.Consumer
import org.slf4j.LoggerFactory
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler
import org.springframework.kafka.listener.ListenerExecutionFailedException
import org.springframework.messaging.Message
import org.springframework.stereotype.Component

@Component("genericConsumerErrorHandler")
class GenericConsumerErrorHandler : ConsumerAwareListenerErrorHandler {
    companion object {
        private val logger = LoggerFactory.getLogger(GenericConsumerErrorHandler::class.java)
    }
    override fun handleError(
        message: Message<*>,
        exception: ListenerExecutionFailedException,
        consumer: Consumer<*, *>
    ): Any {
        logger.error("error", exception)
        return true
    }
}
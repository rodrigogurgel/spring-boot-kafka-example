package br.com.rodrigogurgel.springbootkafkaexample.config

import br.com.rodrigogurgel.springbootkafkaexample.avro.Order
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
class KafkaProducerConfig {

    @Bean
    fun orderKafkaTemplate(factory: ProducerFactory<String, Order>): KafkaTemplate<String, Order> {
        return KafkaTemplate(factory)
    }
}
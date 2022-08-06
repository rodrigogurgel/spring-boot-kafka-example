package br.com.rodrigogurgel.springbootkafkaexample.domain

import java.util.UUID

data class Order(
    val id: UUID,
    val productId: UUID,
    val quantity: Int
)

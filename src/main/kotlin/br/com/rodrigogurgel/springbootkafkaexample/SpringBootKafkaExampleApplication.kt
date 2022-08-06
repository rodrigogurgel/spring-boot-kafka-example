package br.com.rodrigogurgel.springbootkafkaexample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootKafkaExampleApplication

fun main(args: Array<String>) {
	runApplication<SpringBootKafkaExampleApplication>(*args)
}

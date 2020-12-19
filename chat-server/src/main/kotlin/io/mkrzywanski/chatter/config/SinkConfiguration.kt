package io.mkrzywanski.chatter.config

import io.mkrzywanski.chatter.message.Message
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.core.publisher.Sinks

@Configuration
class SinkConfiguration {
    @Bean
    fun sink() : Sinks.Many<Message> = Sinks.many().multicast().onBackpressureBuffer()
}
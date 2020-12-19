package io.mkrzywanski.chatter.websocket

import com.fasterxml.jackson.databind.ObjectMapper
import io.mkrzywanski.chatter.message.Message
import org.springframework.stereotype.Component
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketSession
import reactor.core.publisher.Mono
import reactor.core.publisher.Sinks

@Component
class ReactiveWebSocketHandler(private val sink: Sinks.Many<Message>,
                               private val objectMapper: ObjectMapper) : WebSocketHandler {

    override fun handle(session: WebSocketSession): Mono<Void> {

        val input = session.receive()
                .doOnNext {
                    sink.emitNext(
                            fromJson(it.payloadAsText, Message::class.java),
                            retryOnNonSerializedElse(Sinks.EmitFailureHandler.FAIL_FAST)
                    )
                }
                .then()
        val output = session.send(sink.asFlux().map { message -> session.textMessage(toJson(message)) })

        return Mono.zip(input, output).then()
    }

    fun toJson(obj: Any): String = objectMapper.writeValueAsString(obj)

    fun <T> fromJson(json: String, clazz: Class<T>): T {
        return objectMapper.readValue(json, clazz)
    }

}
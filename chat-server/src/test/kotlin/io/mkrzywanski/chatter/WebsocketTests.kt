package io.mkrzywanski.chatter

import com.fasterxml.jackson.databind.ObjectMapper
import io.mkrzywanski.chatter.config.WEBSOCKET_PATH
import io.mkrzywanski.chatter.message.Message
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.web.reactive.socket.WebSocketSession
import org.springframework.web.reactive.socket.client.StandardWebSocketClient
import org.springframework.web.reactive.socket.client.WebSocketClient
import reactor.core.publisher.Mono
import reactor.core.publisher.Sinks
import reactor.core.scheduler.Schedulers
import java.net.URI
import java.net.URISyntaxException
import java.time.Duration
import java.util.*


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Execution(ExecutionMode.SAME_THREAD)
class WebsocketTest {

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var sink: Sinks.Many<Message>

    @LocalServerPort
    private lateinit var port: String

    val testMessage = Message(
            UUID.fromString("5d4549f9-4bcb-458d-a7fc-15274ff24bc8"),
            "testUser",
            "content"
    )

    @Test
    fun shouldPublishMessageToSinkWhenClientSendsMessage() {

        val webSocketClient: WebSocketClient = StandardWebSocketClient()

        webSocketClient.execute(getUrl(WEBSOCKET_PATH)) { session: WebSocketSession ->
            session.send(Mono.just(session.textMessage(toJson(testMessage))))
        }.subscribeOn(Schedulers.boundedElastic())
                .timeout(Duration.ofMillis(5000))
                .subscribe()

        val publishedMessage = sink.asFlux().blockFirst(Duration.ofMillis(5000))
        assertThat(publishedMessage).isEqualTo(testMessage)
    }

    @Test
    fun oneClientShouldReciveMessageSentByAnotherClient() {

        val sink = Sinks.many().replay().limit<Message>(1)
        val receivingClient: WebSocketClient = StandardWebSocketClient()

        receivingClient.execute(getUrl(WEBSOCKET_PATH)) { session: WebSocketSession ->
            session.receive()
                    .map { fromJson(it.payloadAsText, Message::class.java) }
                    .doOnNext { sink.emitNext(it, Sinks.EmitFailureHandler.FAIL_FAST) }
                    .then()
        }
                .subscribeOn(Schedulers.boundedElastic())
                .timeout(Duration.ofMillis(10000))
                .subscribe()

        val sendingClient: WebSocketClient = StandardWebSocketClient()

        sendingClient.execute(getUrl(WEBSOCKET_PATH)) { session: WebSocketSession ->
            session.send(Mono.just(session.textMessage(toJson(testMessage))))
        }.block(Duration.ofMillis(5000))

        val receivedMessage = sink.asFlux().blockFirst(Duration.ofMillis(5000));
        assertThat(receivedMessage).isEqualTo(testMessage)
    }

    fun toJson(obj: Any): String = objectMapper.writeValueAsString(obj)

    @Throws(URISyntaxException::class)
    protected fun getUrl(path: String): URI {
        return URI("ws://localhost:$port$path")
    }

    fun <T> fromJson(json: String, clazz: Class<T>): T {
        return objectMapper.readValue(json, clazz)
    }
}
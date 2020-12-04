package io.mkrzywanski.chatter.config

import io.mkrzywanski.chatter.message.Message
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.HandlerMapping
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping
import org.springframework.web.reactive.socket.WebSocketHandler
import java.util.*


@Configuration
class Configuration(@Autowired
                    private val webSocketHandler: WebSocketHandler) {
    @Bean
    fun webSocketHandlerMapping(): HandlerMapping? {
        val map: MutableMap<String, WebSocketHandler?> = HashMap()
        map[WEBSOCKET_PATH] = webSocketHandler
        val handlerMapping = SimpleUrlHandlerMapping()
        handlerMapping.order = 1
        handlerMapping.urlMap = map
        return handlerMapping
    }
}
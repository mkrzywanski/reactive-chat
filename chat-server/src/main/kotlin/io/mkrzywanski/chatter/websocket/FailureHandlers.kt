package io.mkrzywanski.chatter.websocket

import reactor.core.publisher.SignalType
import reactor.core.publisher.Sinks.EmitFailureHandler
import reactor.core.publisher.Sinks.EmitResult
import java.util.concurrent.locks.LockSupport

fun retryOnNonSerializedElse(fallback: EmitFailureHandler): EmitFailureHandler {
    return EmitFailureHandler {
        signalType: SignalType?, emitResult: EmitResult ->
        if (emitResult == EmitResult.FAIL_NON_SERIALIZED) {
            LockSupport.parkNanos(10)
            true
        } else fallback.onEmitFailure(signalType!!, emitResult)
    }
}
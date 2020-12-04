package io.mkrzywanski.chatter.message

import java.time.Instant
import java.util.*


data class Message(val id: UUID = UUID.randomUUID(),
                   val userName: String,
                   val content: String,
                   val timestamp : Instant = Instant.now()) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Message

        if (id != other.id) return false
        if (userName != other.userName) return false
        if (content != other.content) return false
        if (timestamp != other.timestamp) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + userName.hashCode()
        result = 31 * result + content.hashCode()
        result = 31 * result + timestamp.hashCode()

        return result
    }
}
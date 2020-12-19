package io.mkrzywanski.chatter.message

import java.util.*


data class Message(val id: UUID,
                   val userId: UUID,
                   val content: String,
                   val chatId: UUID) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Message

        if (id != other.id) return false
        if (userId != other.userId) return false
        if (content != other.content) return false
        if (chatId != other.chatId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + userId.hashCode()
        result = 31 * result + content.hashCode()
        result = 31 * result + chatId.hashCode()
        return result
    }
}
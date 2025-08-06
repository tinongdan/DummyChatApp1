package com.example.dummychatapp1.model

data class Message(
    val roomId: String = "",
    val messageId: String = "",
    val senderId: String = "",
    val content: String = "",
    val mediaUrl: String = "",
    val sendAt: Long = System.currentTimeMillis(),
    val isRead: Boolean = false,
    val userInfo: UserMessage
)

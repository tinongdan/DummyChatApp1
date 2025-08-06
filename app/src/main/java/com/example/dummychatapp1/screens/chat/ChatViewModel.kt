package com.example.dummychatapp1.screens.chat

import androidx.lifecycle.ViewModel
import com.example.dummychatapp1.model.Message
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ChatState(
    val currentMessage: String = "",
    val messages: List<Message> = emptyList(),
)

class ChatViewModel(): ViewModel(){
    private val _state = MutableStateFlow(ChatState())
    val state = _state.asStateFlow()

    fun setMessage(message: String) {
        _state.update {
            it.copy(
                currentMessage = message
            )
        }
    }

    fun sendMessage(toUserId: String) {

    }

    fun fetchMessage(
        currentUserId: String,
        otherUserId: String,
    ) {

    }
}


package com.example.dummychatapp1.screens.chat

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.dummychatapp1.model.Message
import com.example.dummychatapp1.model.UserMessage
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ChatState(
    val currentMessage: String = "",
    val currentImageUrl: String = "",
    val messages: List<Message> = emptyList(),
)

class ChatViewModel(): ViewModel(){
    private val _state = MutableStateFlow(ChatState())
    val state = _state.asStateFlow()

    private val sampleMessages = listOf(
        Message(
            roomId = "room_001",
            messageId = "msg_001",
            senderId = "user_001",
            content = "",
            mediaUrl = "https://img.freepik.com/free-photo/fuji-mountain-kawaguchiko-lake-morning-autumn-seasons-fuji-mountain-yamanachi-japan_335224-102.jpg?semt=ais_hybrid&w=740&q=80",
            sendAt = System.currentTimeMillis() - 300000,
            isRead = true,
            userInfo = UserMessage(
                userId = "user_001",
                avatarUrl = "https://png.pngtree.com/png-clipart/20230927/original/pngtree-man-avatar-image-for-profile-png-image_13001877.png"
            )
        ),
        Message(
            roomId = "room_001",
            messageId = "msg_001",
            senderId = "user_001",
            content = "Xin chào! Bạn có khỏe không?",
            mediaUrl = "",
            sendAt = System.currentTimeMillis() - 300000, // 5 phút trước
            isRead = true,
            userInfo = UserMessage(
                userId = "user_001",
                avatarUrl = "https://png.pngtree.com/png-clipart/20230927/original/pngtree-man-avatar-image-for-profile-png-image_13001877.png"
            )
        ),
        Message(
            roomId = "room_001",
            messageId = "msg_002",
            senderId = "user_002",
            content = "Chào bạn! Mình khỏe, cảm ơn bạn nhé",
            mediaUrl = "",
            sendAt = System.currentTimeMillis() - 240000, // 4 phút trước
            isRead = true,
            userInfo = UserMessage(
                userId = "user_002",
                avatarUrl = "https://i.pinimg.com/736x/c0/4b/01/c04b017b6b9d1c189e15e6559aeb3ca8.jpg"
            )
        ),
        Message(
            roomId = "room_001",
            messageId = "msg_003",
            senderId = "user_001",
            content = "Hôm nay bạn có rảnh không? Mình muốn hẹn gặp",
            mediaUrl = "",
            sendAt = System.currentTimeMillis() - 180000, // 3 phút trước
            isRead = true,
            userInfo = UserMessage(
                userId = "user_001",
                avatarUrl = "https://png.pngtree.com/png-clipart/20230927/original/pngtree-man-avatar-image-for-profile-png-image_13001877.png"
            )
        ),
        Message(
            roomId = "room_001",
            messageId = "msg_004",
            senderId = "user_002",
            content = "Có chứ, mấy giờ bạn muốn gặp?",
            mediaUrl = "",
            sendAt = System.currentTimeMillis() - 120000, // 2 phút trước
            isRead = false,
            userInfo = UserMessage(
                userId = "user_002",
                avatarUrl = "https://i.pinimg.com/736x/c0/4b/01/c04b017b6b9d1c189e15e6559aeb3ca8.jpg"
            )
        ),
        Message(
            roomId = "room_001",
            messageId = "msg_005",
            senderId = "user_001",
            content = "3 giờ chiều được không?",
            mediaUrl = "",
            sendAt = System.currentTimeMillis() - 60000, // 1 phút trước
            isRead = false,
            userInfo = UserMessage(
                userId = "user_001",
                avatarUrl = "https://png.pngtree.com/png-clipart/20230927/original/pngtree-man-avatar-image-for-profile-png-image_13001877.png"
            )
        )
    )

    init {
        _state.update {
            it.copy(messages = sampleMessages)
        }

        //observeMessages("room1")
    }

    fun setMessage(message: String) {
        _state.update {
            it.copy(
                currentMessage = message
            )
        }
    }

    fun sendMessage(toUserId: String) {
        val currentState = _state.value
        if (currentState.currentMessage.isNotBlank()) {
            val newMessage = Message(
                roomId = "room_001",
                messageId = "msg_${System.currentTimeMillis()}",
                senderId = "user_001",
                content = currentState.currentMessage,
                mediaUrl = "",
                sendAt = System.currentTimeMillis(),
                isRead = false,
                userInfo = UserMessage(
                    userId = "user_001",
                    avatarUrl = "https://png.pngtree.com/png-clipart/20230927/original/pngtree-man-avatar-image-for-profile-png-image_13001877.png"
                )
            )

            _state.update {
                it.copy(
                    messages = it.messages + newMessage,
                    currentMessage = ""
                )
            }
        }
    }

    fun SendMessageFirebase(roomId: String, message: Message) {
        val db = Firebase.firestore
        val messageId = "msg_${System.currentTimeMillis()}"

        db.collection("chats")
            .document(roomId)
            .collection("messages")
            .document(messageId)
            .set(message)

    }

    fun sendImageMessage(imageUri: Uri) {
        val newMessage = Message(
            roomId = "room_001",
            messageId = "msg_${System.currentTimeMillis()}",
            senderId = "user_001",
            content = "",
            mediaUrl = imageUri.toString(),
            sendAt = System.currentTimeMillis(),
            isRead = false,
            userInfo = UserMessage(
                userId = "user_001",
                avatarUrl = "https://example.com/avatar.png"
            )
        )

        _state.update {
            it.copy(
                messages = it.messages + newMessage
            )
        }
    }

    fun observeMessages(roomId: String) {
        val db = Firebase.firestore

        db.collection("chats")
            .document(roomId)
            .collection("messages")
            .orderBy("sendAt")
            .addSnapshotListener { snapshot, e ->
                if (e != null || snapshot == null) return@addSnapshotListener

                val messages = snapshot.documents.mapNotNull {
                    it.toObject(Message::class.java)
                }

                _state.update {
                    it.copy(messages = messages)
                }
            }
    }


    fun fetchMessage(
        currentUserId: String,
        otherUserId: String,
    ) {

    }
}


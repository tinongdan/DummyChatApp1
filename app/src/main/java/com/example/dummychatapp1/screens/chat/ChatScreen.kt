package com.example.dummychatapp1.screens.chat

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.dummychatapp1.model.Message
import com.example.dummychatapp1.screens.chat.components.ChatInputField
import com.example.dummychatapp1.screens.chat.components.ChatMessageItem
import com.example.dummychatapp1.ui.theme.MainColor
import com.example.dummychatapp1.utils.components.TextView
import com.example.dummychatapp1.utils.components.VerticalSpace

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen (
    id: String,
    name: String,
    avatarUrl: String = "",
    viewModel: ChatViewModel,
    popBackStack: () -> Unit,
    modifier: Modifier = Modifier
){
    val state by viewModel.state.collectAsStateWithLifecycle()
    val currentUserId = "user_001";

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(7.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(avatarUrl)
                                .crossfade(true)
                                .build(),
                            contentDescription = "Avatar của $name",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape),
                            error = painterResource(id = android.R.drawable.ic_menu_gallery), // Fallback image
                            placeholder = painterResource(id = android.R.drawable.ic_menu_gallery)
                        )

                        TextView(
                            value = name,
                            color = Color.White
                        )
                    }
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 10.dp)
                            .clickable {
                                popBackStack.invoke()
                            }
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MainColor
                )
            )
        },
        bottomBar = {
            ChatInputField(
                message = state.currentMessage,
                onMessageChange = {
                    viewModel.setMessage(it)
                },
                onSendClick = {
                    viewModel.sendMessage(id)
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(state.messages) { model: Message ->
                VerticalSpace()
                ChatMessageItem(
                    model = model ,
                    isCurrentUser = model.senderId == currentUserId
                )
            }
        }
    }
}
package com.example.dummychatapp1

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dummychatapp1.screens.chat.ChatScreen
import com.example.dummychatapp1.screens.chat.ChatViewModel
import com.example.dummychatapp1.ui.theme.DummyChatApp1Theme

class MainActivity : ComponentActivity() {
    @SuppressLint("ViewModelConstructorInComposable")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DummyChatApp1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ChatScreen(
                        id = "CS001",
                        name = "Phuc",
                        viewModel = ChatViewModel(),
                        popBackStack = {},
                        modifier = Modifier.fillMaxSize().padding(innerPadding)
                    )
                }
            }
        }
    }
}
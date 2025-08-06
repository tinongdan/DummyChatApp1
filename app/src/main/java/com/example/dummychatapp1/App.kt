package com.example.dummychatapp1

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dummychatapp1.screens.chat.ChatScreen
import com.example.dummychatapp1.screens.signin.SignInScreen
import com.example.dummychatapp1.screens.splash.SplashScreen
import com.example.dummychatapp1.ui.theme.DummyChatApp1Theme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun App() {
    DummyChatApp1Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val appState = rememberAppState()
            NavHost(
                navController = appState.navController,
                startDestination = SPLASH_SCREEN,
                modifier = Modifier.padding(innerPadding)
            ) {
                notesGraph(appState)
            }
        }
    }
}

@Composable
fun rememberAppState(navController: NavHostController = rememberNavController()) =
    remember(navController) {
        AppState(navController)
    }

fun NavGraphBuilder.notesGraph(appState: AppState) {
    composable(CHAT_SCREEN) {
        ChatScreen(
            restartApp = { route -> appState.clearAndNavigate(route) }
        )
    }
    composable(SIGN_IN_SCREEN) {
        SignInScreen(
            openAndPopUp = { route, popup -> appState.navigateAndPopup(route, popup) }
        )
    }
    composable(SPLASH_SCREEN) {
        SplashScreen(
            openAndPopUp = { route, popup -> appState.navigateAndPopup(route, popup) }
        )
    }
}
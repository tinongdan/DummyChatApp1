package com.example.dummychatapp1.screens.chat

import com.example.dummychatapp1.SPLASH_SCREEN
import com.example.dummychatapp1.model.service.AccountService
import com.example.dummychatapp1.screens.AppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val accountService: AccountService
) : AppViewModel() {

    fun initialize(restartApp: (String) -> Unit) {
        launchCatching {
            accountService.currentUser.collect { user ->
                if (user == null) {
                    restartApp(SPLASH_SCREEN)
                }
            }
        }
    }

    fun onSignOutClick() {
        launchCatching {
            accountService.signOut()
        }
    }

    fun onDeleteAccountClick() {
        launchCatching {
            accountService.deleteAccount()
        }
    }

}
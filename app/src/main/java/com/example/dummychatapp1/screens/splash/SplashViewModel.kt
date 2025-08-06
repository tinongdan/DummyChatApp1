package com.example.dummychatapp1.screens.splash

import com.example.dummychatapp1.CHAT_SCREEN
import com.example.dummychatapp1.SIGN_IN_SCREEN
import com.example.dummychatapp1.SPLASH_SCREEN
import com.example.dummychatapp1.model.service.AccountService
import com.example.dummychatapp1.screens.AppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val accountService: AccountService
) : AppViewModel() {
    fun onAppStart(openAndPopUp: (String, String) -> Unit) {
        if (accountService.hasUser()) {
            openAndPopUp(CHAT_SCREEN, SPLASH_SCREEN)
        } else {
            openAndPopUp(SIGN_IN_SCREEN, SPLASH_SCREEN)
        }
    }
}
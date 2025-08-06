package com.example.dummychatapp1.screens.signin

import com.example.dummychatapp1.CHAT_SCREEN
import com.example.dummychatapp1.SIGN_IN_SCREEN
import com.example.dummychatapp1.model.service.AccountService
import com.example.dummychatapp1.screens.AppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val accountService: AccountService
) : AppViewModel() {
    val email = MutableStateFlow("")
    val password = MutableStateFlow("")

    fun updateEmail(newEmail: String) {
        email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        password.value = newPassword
    }

    fun onSignInClick(openAndPopUp: (String, String) -> Unit) {
        launchCatching {
            accountService.signIn(email.value, password.value)
            openAndPopUp(CHAT_SCREEN, SIGN_IN_SCREEN)
        }
    }
//
//    fun onSignUpClick(openAndPopUp: (String, String) -> Unit) {
//        openAndPopUp(SIGN_UP_SCREEN, SIGN_IN_SCREEN)
//    }
}
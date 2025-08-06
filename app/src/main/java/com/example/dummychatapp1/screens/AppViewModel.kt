package com.example.dummychatapp1.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class AppViewModel : ViewModel() {

    companion object {
        const val ERROR_TAG = "APP ERROR"
    }

    fun launchCatching(block: suspend CoroutineScope.() -> Unit) = viewModelScope.launch(
        CoroutineExceptionHandler { _, throwable ->
            Log.d(ERROR_TAG, throwable.message.orEmpty())
        },
        block = block
    )
}
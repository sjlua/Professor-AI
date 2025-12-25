package com.sjlua.professor.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sjlua.professor.data.ProfAuthManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Handles the data being shown in the RegistrationScreen (View).
 */
class RegistrationViewModel : ViewModel() {
    // The string automatically emits new changes on modification.
    private val _userName = MutableStateFlow<String>("")
    // Read only version.
    val userName: StateFlow<String> = _userName.asStateFlow()
    // Change value
    fun updateUserName(newValue: String) = viewModelScope.launch {
        _userName.value = newValue
    }

    // The string automatically emits new changes on modification.
    private val _apiKey = MutableStateFlow<String>("")
    // Read only version.
    val apiKey: StateFlow<String> = _apiKey.asStateFlow()
    fun updateApiKey(newKey: String) = viewModelScope.launch {
        _apiKey.value = newKey
    }

    /**
     * Stores the user's name and API key
     */
    fun registerUser(context: Context) = viewModelScope.launch {
        ProfAuthManager.setUserName(context, userName.value)
        ProfAuthManager.setApiKey(context, apiKey.value)
    }
}
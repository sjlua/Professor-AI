package com.sjlua.professor.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sjlua.professor.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.sjlua.professor.data.ProfAuthManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Handles the data being shown in the RegistrationScreen (View).
 */
class HomeViewModel : ViewModel() {
    // The string automatically emits new changes on modification.
    private val _prompt = MutableStateFlow<String>("")
    // Read only version.
    val prompt: StateFlow<String> = _prompt.asStateFlow()
    // Change value
    fun updatePrompt(newValue: String) = viewModelScope.launch {
        _prompt.value = newValue
    }

    /**
     * Instance of Gemini model
     */
    private val generativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = BuildConfig.GEMINI_API_KEY
    )

    // Latest AI response
    private val _professorResponse = MutableStateFlow<String>("")
    val professorResponse: StateFlow<String> = _professorResponse.asStateFlow()

    // Holds the current state of the AI response.
    private val _responseLoadingState: MutableStateFlow<UIState> = MutableStateFlow(UIState.Initial) // mutable within ViewModel
    val responseLoadingState: StateFlow<UIState> = _responseLoadingState.asStateFlow() // immutable

    // Send prompt
    fun sendPrompt(context: Context) {
        if (prompt.value.isEmpty()) {
            _responseLoadingState.value = UIState.Error("Please enter a question.")
        } else {
            // Set UI state to loading
            _responseLoadingState.value = UIState.Loading

            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val response = generativeModel.generateContent(
                        content {
                            text("Name is: " + ProfAuthManager.getUserName(context) + ". Answer in 300 characters or less: " + prompt.value)
                        }
                    )

                    response.text?. let { outputContent ->
                        _responseLoadingState.value = UIState.Success(outputContent)
                        _professorResponse.value = outputContent
                    }

                } catch (e: Exception) {
                    _responseLoadingState.value = UIState.Error(e.localizedMessage ?: "")
                    _professorResponse.value = "Error."
                }
            }
        }
    }
}
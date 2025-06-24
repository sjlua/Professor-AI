package com.sjlua.professor.viewmodel

/**
 * UI State management
 */
sealed interface UIState {
    /**
     * Empty state when screen is first shown
     */
    object Initial : UIState

    /**
     * Loading response
     */
    object Loading : UIState

    /**
     * Text has been generated successfully
     */
    data class Success(val outputText: String) : UIState

    /**
     * Task has failed with an error message
     */
    data class Error(val errorMessage: String) : UIState
}
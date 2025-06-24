package com.sjlua.professor.data

import android.content.Context
import androidx.core.content.edit

/**
 * Manages the use of SharedPreferences.
 * ProfAuthManager stores information about the current logged in user
 */
object ProfAuthManager {
    /**
     * Returns the current user's name
     */
    fun getUserName(context: Context): String {
        val sharedPreferences = context.getSharedPreferences("ProfessorSP", Context.MODE_PRIVATE)

        val userName = sharedPreferences.getString("userName", "")

        return userName.toString()
    }

    /**
     * Updates the user's name
     */
    fun setUserName(context: Context, userName: String) {
        context.getSharedPreferences("ProfessorSP", Context.MODE_PRIVATE).edit {
            putString("userName", userName)
        }
    }
}
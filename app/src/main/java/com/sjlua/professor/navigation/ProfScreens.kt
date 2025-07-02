package com.sjlua.professor.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

// These simply replace the string routes (i.e "WelcomeScreen") with a less human-error prone version,
// thanks to Serializable objects allowing autofill out.
// composable("WelcomeScreen") { } is now
// composable<WelcomeScreen> { } is now
@Serializable
object Welcome

@Serializable
object Registration

@Serializable
object Home

@Serializable
object Settings

/**
 * This is used to store all the available routes/pages in the bottom bar.
 * It will store the Serializable object, the icon and the name when in the bottom bar.
 */
data class BottomBarEntry<T : Any>(val route: T, val icon: ImageVector, val name: String)
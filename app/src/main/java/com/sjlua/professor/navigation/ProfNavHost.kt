package com.sjlua.professor.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sjlua.professor.data.ProfAuthManager
import com.sjlua.professor.view.HomeScreen
import com.sjlua.professor.view.RegistrationScreen
import com.sjlua.professor.view.WelcomeScreen

/**
 * The ProfNavHost is in charge of defining actual navigation flow between Composables.
 * NavHostController handles the backend of the navigation.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfNavHost(navController: NavHostController, innerPadding: PaddingValues) {
    // Composable context
    val context = LocalContext.current

    // Calculate starting page outside of NavHost creation
    val startPage = if (ProfAuthManager.getUserName(context).isEmpty()) {
        Welcome
    } else {
        Home
    }

    NavHost(
        navController = navController,
        startDestination = startPage,
        modifier = Modifier.padding(innerPadding)
    ) {
        // Opens the WelcomeScreen screen
        composable<Welcome> {
            WelcomeScreen(
                navigateRegistration = { navController.navigate(Registration) }
            )
        }

        composable<Registration> {
            RegistrationScreen(
                navigateHome = { navController.navigate(Home) }
            )
        }

        composable<Home> {
            HomeScreen(navController)
        }
    }
}
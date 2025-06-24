package com.sjlua.professor.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfNavBar(
    navController: NavHostController,
//    appScrollBehaviour: BottomAppBarScrollBehavior
) {
    // Store all navigation pages
    val bottomBarEntries = listOf(
        BottomBarEntry(Welcome, Icons.Outlined.Home, "Welcome"),
        BottomBarEntry(Home, Icons.Outlined.Search, "Professor"),
        BottomBarEntry(Registration, Icons.Outlined.AccountBox, "My Account")
    )

    // Compose Bottom App Bar
    BottomAppBar(
//        scrollBehavior = appScrollBehaviour
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        bottomBarEntries.forEachIndexed { i, bottomBarRoute ->
            NavigationBarItem(
                icon = { Icon(bottomBarRoute.icon, contentDescription = bottomBarRoute.name) },
                label = { Text(bottomBarRoute.name) },
                selected = currentDestination?.hierarchy?.any {
                    // Checks if the current route is the serializable
                    it.hasRoute(bottomBarRoute.route::class)
                } == true,
                onClick = {
                    // Navigate to the new screen
                    navController.navigate(bottomBarRoute.route) {
                        // Prevent multiple launches of the same screen
                        launchSingleTop = true
                        // Restore state when selecting a composable in the back stack already
                        restoreState = true
                    }
                }
            )
        }
    }
}
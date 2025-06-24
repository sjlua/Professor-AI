package com.sjlua.professor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sjlua.professor.navigation.ProfNavHost
import com.sjlua.professor.ui.theme.ProfessorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Handle navigation states
            val navController: NavHostController = rememberNavController()

            ProfessorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // ProfNavHost decides the screen shown
                    ProfNavHost(navController, innerPadding)
                }
            }
        }
    }
}
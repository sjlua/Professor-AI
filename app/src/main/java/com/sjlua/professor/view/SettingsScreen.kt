package com.sjlua.professor.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sjlua.professor.data.ProfAuthManager
import com.sjlua.professor.ui.theme.ProfessorTheme
import com.sjlua.professor.viewmodel.RegistrationViewModel

/**
 * Settings screen
 */
@Composable
fun SettingsScreen(
    navigateHome: () -> Unit
) {
    // Composable context
    val context = LocalContext.current

    // Instantiate the Settings ViewModel
    // Currently offers the same functionality as Register, so reusing the ViewModel
    val settingsViewModel = viewModel<RegistrationViewModel>()

    // Must be called once
    LaunchedEffect(Unit) {
        settingsViewModel.updateUserName(ProfAuthManager.getUserName(context))
        settingsViewModel.updateApiKey(ProfAuthManager.getApiKey(context))
    }

    // Stores the following variable StateFlows in a read-only state.
    val userName = settingsViewModel.userName.collectAsState()
    val apiKey = settingsViewModel.apiKey.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                shape = FloatingActionButtonDefaults.largeShape,
                onClick = {
                    settingsViewModel.registerUser(context)
                    navigateHome()
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Check,
                    contentDescription = ("Update your details")
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Your settings",
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    style = TextStyle(lineHeight = 40.sp)
                )
            }

            item { Spacer(modifier = Modifier.padding(16.dp)) }

            item {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = userName.value,
                    label = { Text("Your name") },
                    supportingText = {
                        if (userName.value.isEmpty()) {
                            Text("Enter your name!")
                        }
                    },
                    onValueChange = { settingsViewModel.updateUserName(it) }
                )
            }

            item { Spacer(modifier = Modifier.padding(16.dp)) }

            item {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = apiKey.value,
                    label = { Text("Your Gemini API key (optional)") },
                    onValueChange = { settingsViewModel.updateApiKey(it) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsPreview() {
    ProfessorTheme {
        SettingsScreen({ null })
    }
}
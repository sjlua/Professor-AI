package com.sjlua.professor.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.sjlua.professor.ui.theme.ProfessorTheme
import com.sjlua.professor.viewmodel.RegistrationViewModel

/**
 * Registration screen
 */
@Composable
fun RegistrationScreen(
    navigateHome: () -> Unit
) {
    // Instantiate the Registration ViewModel
    val registrationViewModel = viewModel<RegistrationViewModel>()

    // Stores the following variable StateFlows in a read-only state.
    val userName = registrationViewModel.userName.collectAsState()
    val apiKey = registrationViewModel.apiKey.collectAsState()

    // Composable context
    val context = LocalContext.current

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                shape = FloatingActionButtonDefaults.largeShape,
                onClick = {
                    registrationViewModel.registerUser(context)
                    navigateHome()
                }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.ArrowForward,
                    contentDescription = ("Submit your details")
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
                    text = "Let's get some things filled out first.",
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
                    onValueChange = { registrationViewModel.updateUserName(it) }
                )
            }

            item { Spacer(modifier = Modifier.padding(16.dp)) }

            item {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = apiKey.value,
                    label = { Text("Your Gemini API key (optional)") },
                    onValueChange = { registrationViewModel.updateApiKey(it) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationPreview() {
    ProfessorTheme {
        RegistrationScreen({ null })
    }
}
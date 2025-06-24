package com.sjlua.professor.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sjlua.professor.R
import com.sjlua.professor.navigation.ProfNavBar
import com.sjlua.professor.ui.theme.ProfessorTheme
import com.sjlua.professor.viewmodel.HomeViewModel
import com.sjlua.professor.viewmodel.UIState

/**
 * The Professor home screen
 */
@Composable
fun HomeScreen(
    navController: NavHostController
) {
    // Instantiate the Home ViewModel
    val homeViewModel = viewModel<HomeViewModel>()

    // Stores the following variable StateFlows in a read-only state.
    val prompt = homeViewModel.prompt.collectAsState()
    val responseLoadingState = homeViewModel.responseLoadingState.collectAsState()
    val professorResponse = homeViewModel.professorResponse.collectAsState()

    // Composable context
    val context = LocalContext.current

    Scaffold(
        bottomBar = { ProfNavBar(navController) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .focusable(true),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            item {
                Image(
                    painter = painterResource(R.drawable.professorai),
                    contentDescription = "The Professor"
                )
            }

            item { Spacer(modifier = Modifier.padding(16.dp)) }

            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Have a question?",
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )
            }

            item { Spacer(modifier = Modifier.padding(8.dp)) }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedTextField(
                        modifier = Modifier.padding(end = 8.dp).weight(7f),
                        value = prompt.value,
                        maxLines = 4,
                        label = { Text("Type your question here") },
                        onValueChange = { homeViewModel.updatePrompt(it) }
                    )

                    IconButton(
                        modifier = Modifier.weight(1f),
                        onClick = { homeViewModel.sendPrompt(context) }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.Send,
                            contentDescription = ("Submit your question")
                        )
                    }
                }
            }

            item { Spacer(modifier = Modifier.padding(8.dp)) }

            if (responseLoadingState.value is UIState.Loading) {
                item {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "I'm thinking...",
                        fontStyle = FontStyle.Italic,
                        fontSize = 20.sp
                    )
                }
            } else if (responseLoadingState.value is UIState.Success) {
                item {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = professorResponse.value
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    ProfessorTheme {
        val navController = rememberNavController()
        HomeScreen(navController)
    }
}
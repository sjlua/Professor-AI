package com.sjlua.professor.view

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sjlua.professor.R
import com.sjlua.professor.ui.theme.ProfessorTheme

/**
 * The first screen for a new user.
 */
@Composable
fun WelcomeScreen(
    navigateRegistration: () -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                shape = FloatingActionButtonDefaults.largeShape,
                onClick = { navigateRegistration() }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.ArrowForward,
                    contentDescription = ("Next page")
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
                Image(
                    painter = painterResource(R.drawable.professorai),
                    contentDescription = "The Professor"
                )
            }

            item { Spacer(modifier = Modifier.padding(16.dp)) }

            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Hello, I'm the Professor, it's nice to meet you.",
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    style = TextStyle(lineHeight = 40.sp)
                )
            }

            item { Spacer(modifier = Modifier.padding(16.dp)) }

            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "저는 프러패서예요. 만나서 반가워요!",
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    style = TextStyle(lineHeight = 40.sp)
                )
            }

            item { Spacer(modifier = Modifier.padding(16.dp)) }

            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Shall we get started?",
                    fontSize = 20.sp
                )
            }

            item { Spacer(modifier = Modifier.padding(16.dp)) }

            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "시작 할까요?",
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomePreview() {
    ProfessorTheme {
        WelcomeScreen({ null })
    }
}
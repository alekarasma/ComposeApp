package com.asmaa.composeapp.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navigateToNextScreen: () -> Unit) {

    val animationState = remember { mutableStateOf(0f) }
    // Use LaunchedEffect when you need to run asynchronous operations and update the UI based on their results.
    // Use DisposableEffect for cleanup tasks when a composable exits.
    LaunchedEffect(animationState) {
        delay(3000)
        navigateToNextScreen()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFD700),
                        Color.White
                    ),
                )
            ),
        contentAlignment = Alignment.Center,
    )
    {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Welcome to Orange & Co.",
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                color = Color.Magenta,
            )
            Text(
                text = "Get ready for fun and excitement!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
            )
        }

    }

}

@Preview
@Composable
fun SplashscreenPreview() {
    SplashScreen {

    }
}
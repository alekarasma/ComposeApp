package com.asmaa.composeapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.asmaa.composeapp.ui.InviteViewModel

@Composable
fun InviteCancelScreen(viewModel: InviteViewModel, navigateToNextScreen: () -> Unit) {
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
    ) {
        Column(Modifier, Arrangement.Center, Alignment.Start) {
            Text("Congratulations ${viewModel.username}.\nYour application is Successful!!!.\nStay tuned for tangy and juicy updates")
            Spacer(modifier = Modifier)
            OutlinedButton(onClick = navigateToNextScreen) {
                Text(text = "Cancel Invite")
            }
        }
    }
}

@Preview
@Composable
fun InviteCancelPreview() {
    val viewModel: InviteViewModel = hiltViewModel()
    InviteCancelScreen(viewModel, {})
}
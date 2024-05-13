package com.asmaa.composeapp.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.asmaa.composeapp.ui.InviteViewModel

/**
 * How to define navigation routes for multi screen app in compose
 */
@Composable
fun InviteCancelScreen(
    viewModel: InviteViewModel,
    navigateToPreviousScreen: () -> Unit,
    navigateToNextScreen: () -> Unit
) {
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
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedButton(onClick = {
                viewModel.deleteInviteFromCompany()
                navigateToPreviousScreen()
            }) {

                Text(text = "Cancel Invite")
            }
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedButton(onClick = {
                navigateToNextScreen()
                // Call ViewModel Check User's list
                viewModel.listRegisteredUsers()
                // If call result successful, go to ListUsersScreen
                // else display error ,first do this and then go to recyclerView
            }) {
                Text(text = "Check list of other User's Invited")
            }
        }
    }
}

@Preview
@Composable
fun InviteCancelPreview() {
    val viewModel: InviteViewModel = hiltViewModel()
    InviteCancelScreen(viewModel, {
    }, {

    })
}
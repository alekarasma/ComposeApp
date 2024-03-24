package com.asmaa.composeapp.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.asmaa.composeapp.ui.InviteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InviteScreen(inviteViewModel: InviteViewModel, navigateToNextScreen: () -> Unit) {


    // we used rememberSaveable to retain the state across configuration changes, such as device rotation or when the app goes into the background,
    // we want to preserve error state
    var isError by rememberSaveable { mutableStateOf(false) }

    val inviteStatus by inviteViewModel.inviteStatus.observeAsState()

    LaunchedEffect(inviteStatus) { // Observe inviteStatus.value
        when (inviteStatus) { // Access value property
            is InviteViewModel.InviteStatus.Success -> {
                navigateToNextScreen() // Invoke the lambda to navigate
            }

            is InviteViewModel.InviteStatus.Failure -> {
                // Handle failure case
            }

            else -> {
                Log.e("TAG", "Unknown or null value")
            }
        }
    }

    isError =
        inviteViewModel.confirmPassword.length > 5 && inviteViewModel.password != inviteViewModel.confirmPassword


    // If already log in , display a message- already signed up
    if (inviteViewModel.userLoggedIn) {

    } else {
        //else log in screen
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
            Column(Modifier, Arrangement.Center, Alignment.CenterHorizontally) {
                OutlinedTextField(
                    value = inviteViewModel.username,  // Use the username state variable
                    onValueChange = { inviteViewModel.updateUsername(it) },
                    label = { Text("Enter Username") }
                )
                OutlinedTextField(
                    modifier = Modifier,
                    value = inviteViewModel.password,  // Use the password state variable
                    onValueChange = { inviteViewModel.updatePassword(it) },  // Ensure onValueChange captures input
                    label = { Text("Enter Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    textStyle = TextStyle.Default,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    ),
                    isError = inviteViewModel.password.length in 1..5,
                    supportingText = {
                        if (inviteViewModel.password.length in 1..5) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "Password less than 6 characters",
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                )
                OutlinedTextField(
                    value = inviteViewModel.confirmPassword,
                    onValueChange = { inviteViewModel.updateConfirmPassword(it) },
                    label = { Text("Confirm Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    textStyle = TextStyle.Default,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    isError = isError,
                    supportingText = {
                        if (isError) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "Password does not match",
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                )

                OutlinedButton(enabled = !isError, onClick = {
                    inviteViewModel.requestInviteFromCompany()
                }) {
                    Text(text = "Sign up")
                }
            }
        }
    }
}


@Preview
@Composable
fun InviteScreenPreview() {
    //InviteScreen {
    //  }
}
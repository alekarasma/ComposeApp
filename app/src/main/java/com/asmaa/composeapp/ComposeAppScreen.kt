package com.asmaa.composeapp

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.asmaa.composeapp.ui.InviteViewModel
import com.asmaa.composeapp.ui.screens.InviteCancelScreen
import com.asmaa.composeapp.ui.screens.InviteScreen
import com.asmaa.composeapp.ui.screens.SplashScreen

enum class ComposeAppScreen {
    SPLASHSCREEN,
    INVITE_REQUEST,
    INVITE_CANCEL
}

@Composable
fun ComposeApp(navController: NavHostController = rememberNavController()) {
    val inviteViewModel: InviteViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = ComposeAppScreen.SPLASHSCREEN.name,
        modifier = Modifier.padding()
    ) {

        composable(ComposeAppScreen.SPLASHSCREEN.name) {
            SplashScreen {
                navController.navigate(
                    ComposeAppScreen.INVITE_REQUEST.name
                )
            }
        }
        composable(ComposeAppScreen.INVITE_REQUEST.name) {
            InviteScreen(inviteViewModel) {
                navController.navigate(
                    ComposeAppScreen.INVITE_CANCEL.name
                )
            }
        }

        composable(ComposeAppScreen.INVITE_CANCEL.name) {
            InviteCancelScreen(inviteViewModel) {
                navController.navigate(
                    ComposeAppScreen.INVITE_REQUEST.name
                )
            }
        }
    }
}
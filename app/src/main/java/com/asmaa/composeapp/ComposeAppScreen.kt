package com.asmaa.composeapp

import androidx.compose.foundation.layout.padding
import com.asmaa.composeapp.ui.screens.SplashScreen
import com.asmaa.composeapp.ui.screens.InviteScreen
import com.asmaa.composeapp.ui.screens.InviteCancelScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.asmaa.composeapp.ui.InviteViewModel

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
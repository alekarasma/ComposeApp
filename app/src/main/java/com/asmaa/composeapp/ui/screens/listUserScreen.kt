package com.asmaa.composeapp.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.asmaa.composeapp.model.RegisterResource
import com.asmaa.composeapp.ui.InviteViewModel
import androidx.compose.ui.Modifier
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ListUserScreen(inviteViewModel: InviteViewModel, navigateToNextScreen: () -> Unit) {

    UserDataLazyColumn(userData = inviteViewModel.registeredUsers)
    //Read derivedStateOf(), what is the benefit of key and how can I use it here with sorting etc
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun UserDataLazyColumn(userData: List<RegisterResource>) {
    LazyColumn {
        items(userData) { user ->
            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .heightIn(min = Dp.Unspecified, max = Dp.Infinity),
                colors = stringToCardColors(user.color),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
            ) {
                Text(text = "Name: " + user.name)
                Text(text = "ID: " + user.id.toString())
                Text(text = "Birth Year: " + user.year.toString())
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun stringToCardColors(colorString: String): CardColors {
    val color = Color(android.graphics.Color.parseColor(colorString))
    return CardDefaults.cardColors(color)
}
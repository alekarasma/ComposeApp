package com.asmaa.composeapp.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asmaa.composeapp.data.ApiResponse.Failure
import com.asmaa.composeapp.data.ApiResponse.Success
import com.asmaa.composeapp.data.LoginRepository
import com.asmaa.composeapp.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InviteViewModel @Inject constructor(val loginRepo: LoginRepository) : ViewModel() {

    private val _inviteStatus: MutableLiveData<InviteStatus> = MutableLiveData<InviteStatus>()
    val inviteStatus: LiveData<InviteStatus> = _inviteStatus

    // Mutable (Changeable/Updatable) keep it private and update through function
    // mutableStateOf is mainly used in composed based applications, to observe state changes and manage it
    // mutableStateOf is a function used to hold the mutable state of variable, and it can be observed for changes.
    // Whenever the state changes the compose function depending on it go through recomposition
    var username by mutableStateOf("")
        private set

    fun updateUsername(input: String) {
        username = input
    }

    var password by mutableStateOf("")
        private set

    fun updatePassword(input: String) {
        password = input
    }

    var confirmPassword by mutableStateOf("")
        private set

    fun updateConfirmPassword(input: String) {
        confirmPassword = input
    }

    var userLoggedIn by mutableStateOf(false)

    fun requestInviteFromCompany(
        dateOfBirth: Long = 0L
    ) {
        viewModelScope.launch {
            val user: User = User(username, "adbc@gmail", password, dateOfBirth)
            when (val response = loginRepo.requestInvite(user)) {
                is Success -> {
                    Log.e("TAG", "Successful Invitation")
                    _inviteStatus.value = InviteStatus.Success
                }

                is Failure -> {
                    val errorMessage = response.error
                    Log.e("TAG", "Invite failed: $errorMessage")
                    _inviteStatus.value = InviteStatus.Failure(errorMessage)
                }
            }
        }
    }

    fun deleteInviteFromCompany() {

    }

    sealed class InviteStatus {
        object Success : InviteStatus()
        data class Failure(val msg: String) : InviteStatus()
    }

}
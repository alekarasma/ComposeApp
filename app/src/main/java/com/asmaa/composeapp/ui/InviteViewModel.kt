package com.asmaa.composeapp.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asmaa.composeapp.data.ApiResponse.Failure
import com.asmaa.composeapp.data.ApiResponse.Success
import com.asmaa.composeapp.data.LoginRepository
import com.asmaa.composeapp.model.User
import com.asmaa.composeapp.model.RegisterResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class InviteViewModel @Inject constructor(val loginRepo: LoginRepository) : ViewModel() {

    private val _inviteStatus: MutableLiveData<InviteStatus?> = MutableLiveData<InviteStatus?>()
    val inviteStatus: MutableLiveData<InviteStatus?> = _inviteStatus
    var userLoggedIn by mutableStateOf(false)
    var registeredUsers :List<RegisterResource> by mutableStateOf(listOf())

    /**
     * Check if a user is already logged In
     */
    /*init {

    }*/

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

    /**
     * Sends a request to get the Invite from Company
     */
    fun requestInviteFromCompany(
        dateOfBirth: Long = 0L
    ) {
        _inviteStatus.value = InviteStatus.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val user = User(username, "adbc@gmail", password, dateOfBirth)
            when (val response = loginRepo.requestInvite(user)) {
                is Success -> {
                    Log.e("TAG", "Successful Invitation")
                    withContext(Dispatchers.Main) {
                        _inviteStatus.value = InviteStatus.Success
                    }
                }

                is Failure -> {
                    val errorMessage = response.error
                    Log.e("TAG", "Invite failed: $errorMessage")
                    _inviteStatus.value = InviteStatus.Failure(errorMessage)
                }
            }
        }
    }

    /**
     * Clears the login form
     */
    private fun clearLoginForm() {
        updateUsername("")
        updatePassword("")
        updateConfirmPassword("")
    }

    /**
     * Sends request to delete Invite from Company
     */
    fun deleteInviteFromCompany() {
        _inviteStatus.value = InviteStatus.Loading
        clearLoginForm()
    }

    /**
     * List Registered Users from the company
     * Read about the best methods to receive retrofit response and convert if
     * for your use and make notes
     */
    fun listRegisteredUsers() {
        _inviteStatus.value = InviteStatus.Loading
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = loginRepo.listRegisteredUser("1")) {
                is Success -> {
                    Log.e("TAG", "${response.response.data}")
                    registeredUsers = response.response.data
                    withContext(Dispatchers.Main) {
                        _inviteStatus.value = InviteStatus.Success
                    }
                }

                is Failure -> {

                }
            }
        }

    }

    sealed class InviteStatus {
        object Success : InviteStatus()
        class Failure(val msg: String) : InviteStatus()
        object Loading : InviteStatus()
    }
}
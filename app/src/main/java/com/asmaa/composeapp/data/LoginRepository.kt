package com.asmaa.composeapp.data

import android.util.Log
import androidx.datastore.preferences.protobuf.Api
import com.asmaa.composeapp.model.Email
import com.asmaa.composeapp.model.InviteResponse
import com.asmaa.composeapp.model.RegisteredUsers
import com.asmaa.composeapp.model.User
import com.asmaa.composeapp.model.UserAccountDetails
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
) : LoginRepository {
    val TAG = "LoginRepository"

    //It is possible that the user definition at the View -ViewModel UI level is different, You can transform the data here, to be compatible with the model level here
    //For simplicity of this app we have the same user model in Data layer and UI layer
    override suspend fun requestInvite(user: User): ApiResponse<InviteResponse> {
        Log.i(TAG, "request Invite")
        val userAccountDetails = convertToUserAccountDetails(user)
        return remoteRepository.sendSuccessfulInvite(userAccountDetails)
    }

    override suspend fun listRegisteredUser(page: String): ApiResponse<RegisteredUsers> {
        Log.i(TAG, "List Registered User")
        return remoteRepository.listRegisteredUser(page)
    }

    override suspend fun isUserLoggedIn() {
        Log.i(TAG, "User Already Logged in")
        //localRepository.isUserLoggedIn()
    }

    override suspend fun sendUnSuccessfulInvite(user: User): ApiResponse<InviteResponse> {
        return remoteRepository.sendUnSuccessfulInvite(user.email)
    }

    private fun convertToUserAccountDetails(user: User): UserAccountDetails {
        return UserAccountDetails("eve.holt@reqres.in", "pistol")
    }
}

interface LoginRepository {

    suspend fun requestInvite(user: User): ApiResponse<InviteResponse>

    suspend fun listRegisteredUser(page: String): ApiResponse<RegisteredUsers>

    suspend fun isUserLoggedIn()

    suspend fun sendUnSuccessfulInvite(user: User): ApiResponse<InviteResponse>
}
package com.asmaa.composeapp.data

import android.util.Log
import androidx.datastore.preferences.protobuf.Api
import com.asmaa.composeapp.model.InviteResponse
import com.asmaa.composeapp.model.RegisteredUsers
import com.asmaa.composeapp.model.UserAccountDetails
import retrofit2.HttpException
import retrofit2.http.HTTP
import java.lang.Exception
import javax.inject.Inject

class RemoteRepository @Inject constructor(private val apiService: LoginApiService) {

    suspend fun sendSuccessfulInvite(user: UserAccountDetails): ApiResponse<InviteResponse> {
        Log.e("TAG", "Send Invite")
        try {
            val response = apiService.successfulInvite(user)
            if (response.isSuccessful) {
                return ApiResponse.Success(response.body()!!)
            } else {
                return ApiResponse.Failure(HttpException(response).message())
            }
        } catch (e: Exception) {
            Log.e("TAG", "Exception sending request: $e")
            return ApiResponse.Failure(e.message ?: "Unknown Error")
        }
    }


    suspend fun listRegisteredUser(page: String): ApiResponse<RegisteredUsers> {
        return try {
            apiService.listRegisteredUsers(page)
        } catch (e: Exception) {
            ApiResponse.Failure(e.message ?: "Unknown Error")
        }
    }

    suspend fun sendUnSuccessfulInvite(email: String): ApiResponse<InviteResponse> {
        return try {
            apiService.unSuccessfulInvite(email)
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string() ?: "Unknown Error"
            Log.e("TAG", "HTTP Exception: $errorBody")
            ApiResponse.Failure(errorBody)
        } catch (e: Exception) {
            Log.e("TAG", "Exception: ${e.message}", e)
            ApiResponse.Failure(e.message ?: "Unknown Error")
        }
    }
}


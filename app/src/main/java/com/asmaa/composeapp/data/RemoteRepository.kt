package com.asmaa.composeapp.data

import android.util.Log
import com.asmaa.composeapp.model.InviteResponse
import com.asmaa.composeapp.model.RegisteredResources
import com.asmaa.composeapp.model.UserAccountDetails
import retrofit2.HttpException
import java.lang.Exception
import javax.inject.Inject

class RemoteRepository @Inject constructor(private val apiService: LoginApiService) {

    suspend fun sendSuccessfulInvite(user: UserAccountDetails): ApiResponse<InviteResponse> {
        Log.e("TAG", "Send Invite")
        try {
            val response = apiService.successfulInvite(user)
            return if (response.isSuccessful) {
                ApiResponse.Success(response.body()!!)
            } else {
                ApiResponse.Failure(HttpException(response).message())
            }
        } catch (e: Exception) {
            Log.e("TAG", "Exception sending request: $e")
            return ApiResponse.Failure(e.message ?: "Unknown Error")
        }
    }

    suspend fun listRegisteredUser(page: String): ApiResponse<RegisteredResources> {
        return try {
            val response = apiService.listResources()
            return if (response.isSuccessful) {
                ApiResponse.Success(response.body()!!)
            } else {
                ApiResponse.Failure(HttpException(response).message())
            }
        } catch (e: Exception) {
            ApiResponse.Failure(e.message ?: "Unknown Error")
        }
    }

    suspend fun sendUnSuccessfulInvite(email: String): ApiResponse<String> {
        return try {
            val response = apiService.unSuccessfulInvite(email)
            return if (response.isSuccessful) {
                ApiResponse.Success(response.body()!!)
            } else {
                ApiResponse.Failure(HttpException(response).message())
            }
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


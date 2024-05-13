package com.asmaa.composeapp.data

import com.asmaa.composeapp.model.InviteResponse
import com.asmaa.composeapp.model.RegisteredUsers
import com.asmaa.composeapp.model.UserAccountDetails
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginApiService {
    // Unable to create @Body converter for class com.asmaa.composeapp.model.User - addConverterFactory during building
    // HTPP 400 -bad request
    @POST("/api/register")
    suspend fun successfulInvite(@Body user: UserAccountDetails): Response<InviteResponse>

    @POST("/api/register")
    suspend fun unSuccessfulInvite(@Body email: String): Response<String>

    // If it fails check stackoverflow for the & value is added or not, you may just have to remove ? mark in @POST
    // https://stackoverflow.com/questions/36730086/retrofit-2-url-query-parameter
    @GET("/api/users?")
    suspend fun listResources(@Query("page") page: String): Response<RegisteredUsers>

    @GET("/api/unknown")
    suspend fun listRegisteredUsers(): Response<RegisteredUsers>

    @GET("/api/users")
    suspend fun getUserInfo(@Header("Authorization") authorization: String): Response<UserAccountDetails>
}
package com.asmaa.composeapp.data

sealed class ApiResponse<out T> {
    data class Success<T>(val response: T) : ApiResponse<T>()
    data class Failure(val error: String) : ApiResponse<Nothing>()
}
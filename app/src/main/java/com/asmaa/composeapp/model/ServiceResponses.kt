package com.asmaa.composeapp.model

data class InviteResponse(
    val id: Int,
    val token: String
)

data class UserData(
    val id: Int,
    val name: String,
    val year: Int,
    val color: String,
    val pantoneValue: String
)

data class Support(val url: String, val text: String)


data class RegisteredUsers(
    val page: Int,
    val perPage: Int,
    val total: Int,
    val totalPages: Int,
    val data: List<UserData>,
    val support: Support
)

package com.asmaa.composeapp.model

data class InviteResponse(
    val id: Int,
    val token: String
)

data class RegisterResource(
    val id: Int,
    val name: String,
    val year: Int,
    val color: String,
    val pantoneValue: String
)

data class RegisteredResources(
    val page: Int,
    val perPage: Int,
    val total: Int,
    val totalPages: Int,
    val data: List<RegisterResource>,
    val support: Support
)

data class RegisteredUser(
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatar: String
)

data class RegisteredUsers(
    val page: Int,
    val perPage: Int,
    val total: Int,
    val totalPages: Int,
    val data: List<RegisteredUser>,
    val support: Support
)

data class Support(val url: String, val text: String)
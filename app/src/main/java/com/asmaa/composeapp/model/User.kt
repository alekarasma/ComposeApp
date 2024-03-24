package com.asmaa.composeapp.model

data class User(
    val name: String,
    val email: String,
    val password: String,
    val dateOfBirth: Long
)

data class Email (
    val email: String,
)

data class UserAccountDetails(
    val email: String,
    val password: String
)

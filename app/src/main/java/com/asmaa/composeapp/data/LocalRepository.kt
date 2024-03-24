package com.asmaa.composeapp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.asmaa.composeapp.model.User
import kotlinx.coroutines.flow.first
import javax.inject.Inject

private const val USER_PREFERENCES_NAME = "user_preferences"

private val Context.dataStore by preferencesDataStore(
    name = USER_PREFERENCES_NAME
)
class LocalRepository @Inject() constructor(private val userSessionInfo: DataStore<Preferences>) {

    private var userName = stringPreferencesKey("firstName")
    private val userPassword = stringPreferencesKey("password")
    private val userDOB = longPreferencesKey("birthDate")
    private val userLogInTime = longPreferencesKey("logInTime")
    private val fiveMinutesInMillis = 5 * 60 * 1000L // Convert 5 minutes to milliseconds

    suspend fun saveUserSession(user: User) {
        userSessionInfo.edit { preferences ->
            preferences[userName] = user.name
            preferences[userPassword] = user.password
            preferences[userDOB] = user.dateOfBirth
            preferences[userLogInTime] = System.currentTimeMillis()
        }
    }

    suspend fun removeUserSession() {
        userSessionInfo.edit { it.clear() }
    }

    suspend fun isUserLoggedIn(): Boolean {
        val preferences = userSessionInfo.data.first()
        val logInTime = preferences[userLogInTime] ?: 0L
        return (System.currentTimeMillis() - logInTime) > fiveMinutesInMillis
    }
}
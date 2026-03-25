package com.bbm.ataramor.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Singleton

data class AppSettings(
    val theme: String = "System",
    val language: String = "uk"
)

@Singleton
class SettingsRepository(private val dataStore: DataStore<Preferences>) {
    private object Keys {
        val THEME = stringPreferencesKey("theme")
        val LANGUAGE = stringPreferencesKey("language")
    }

    val settingsFlow: Flow<AppSettings> = dataStore.data
        .catch { exception ->
            if (exception is IOException) emit(emptyPreferences()) else throw exception
        }
        .map { preferences ->
            AppSettings(
                theme = preferences[Keys.THEME] ?: "System",
                language = preferences[Keys.LANGUAGE] ?: "uk"
            )
        }

    suspend fun updateTheme(newTheme: String) {
        dataStore.edit { it[Keys.THEME] = newTheme }
    }
}
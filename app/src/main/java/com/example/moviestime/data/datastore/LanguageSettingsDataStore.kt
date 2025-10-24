package com.example.moviestime.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "language_settings")

class LanguageSettingsDataStore(private val context: Context) {

    private val LANGUAGE_KEY = stringPreferencesKey("app_language")

    val appLanguage: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[LANGUAGE_KEY] ?: "en"
        }

    suspend fun setAppLanguage(language: String) {
        context.dataStore.edit { preferences ->
            preferences[LANGUAGE_KEY] = language
        }
    }
}
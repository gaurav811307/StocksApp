package com.example.stock.utils

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore("stock_pref")

class DataStoreManager @Inject constructor(@ApplicationContext context: Context) {

    private val dataStore = context.dataStore
    private val RECENT_SEARCH_KEY = stringSetPreferencesKey("recent_searches")

    fun getRecentSearches(): Flow<List<String>> {
        return dataStore.data.map { prefs ->
            prefs[RECENT_SEARCH_KEY]?.toList()?.takeLast(10)?.reversed() ?: emptyList()
        }
    }

    suspend fun addSearchQuery(query: String) {
        dataStore.edit { prefs ->
            val current = prefs[RECENT_SEARCH_KEY]?.toMutableSet() ?: mutableSetOf()
            current.add(query)
            prefs[RECENT_SEARCH_KEY] = current
        }
    }
}
package com.bbm.ataramor.features.settings.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import org.koin.core.annotation.Singleton

@Singleton
fun createAndroidDataStore(context: Context): DataStore<Preferences> =
    createDataStore {
        context.filesDir.resolve(DATASTORE_FILE_NAME).absolutePath
    }
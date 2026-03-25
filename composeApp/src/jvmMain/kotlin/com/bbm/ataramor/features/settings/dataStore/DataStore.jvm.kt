package com.bbm.ataramor.features.settings.dataStore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import org.koin.core.annotation.Singleton
import java.io.File

@Singleton
fun createJvmDataStore(): DataStore<Preferences> =
    createDataStore {
        val dataDir = File(System.getProperty("user.home"), ".ataramor")
        if (!dataDir.exists()) dataDir.mkdirs()
        File(dataDir, DATASTORE_FILE_NAME).absolutePath
    }
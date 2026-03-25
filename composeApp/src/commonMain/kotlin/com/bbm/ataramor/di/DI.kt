package com.bbm.ataramor.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.bbm.ataramor.features.settings.dataStore.DATASTORE_FILE_NAME
import com.bbm.ataramor.features.settings.dataStore.createDataStore
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.KoinApplication
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan("com.bbm.ataramor.features.settings")
class SettingsModule

@Module
@ComponentScan("com.bbm.ataramor.data.repository")
class RepositoryModule {

    @Single
    fun provideDataStore(pathProvider: PathProvider): DataStore<Preferences> {
        return createDataStore { pathProvider.getPath() }
    }
}

@Module(
    includes = [
        SettingsModule::class,
        RepositoryModule::class
    ]
)
@ComponentScan("com.bbm.ataramor.di")
class AppModule

@KoinApplication(modules = [AppModule::class])
class KoinApp

interface PathProvider {
    fun getPath(): String
}

@Single
class DesktopPathProvider : PathProvider {
    override fun getPath(): String {
        val home = System.getProperty("user.home")
        return "$home/.ataramor/$DATASTORE_FILE_NAME"
    }
}
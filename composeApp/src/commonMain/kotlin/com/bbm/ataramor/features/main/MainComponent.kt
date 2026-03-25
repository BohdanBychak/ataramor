package com.bbm.ataramor.features.main

import com.arkivanov.decompose.ComponentContext
import com.bbm.ataramor.core.utils.coroutineScope
import com.bbm.ataramor.data.repository.SettingsRepository
import com.bbm.ataramor.features.settings.store.SettingsStore
import kotlinx.coroutines.flow.StateFlow

interface MainComponent {
    val state: StateFlow<MainStore.State>
    fun onIntent(intent: MainStore.Intent)
}

class DefaultMainComponent(
    componentContext: ComponentContext,
    val settings: SettingsRepository,
    private val onShowPuzzles: () -> Unit,
    private val onShowGameWithBot: () -> Unit,
) : MainComponent, ComponentContext by componentContext {
    private val store = MainStore(instanceKeeper.coroutineScope(), repository = settings)

    override val state: StateFlow<MainStore.State> = store.state

    override fun onIntent(intent: MainStore.Intent) {
        store.onIntent(intent)
    }
}
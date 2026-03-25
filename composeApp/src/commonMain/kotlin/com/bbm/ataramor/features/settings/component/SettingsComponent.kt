package com.bbm.ataramor.features.settings.component

import com.arkivanov.decompose.ComponentContext
import com.bbm.ataramor.core.utils.coroutineScope
import com.bbm.ataramor.data.repository.SettingsRepository
import com.bbm.ataramor.features.settings.store.SettingsStore
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

interface SettingsComponent {
    val state: StateFlow<SettingsStore.State>
    fun onIntent(intent: SettingsStore.Intent)
}

class DefaultSettingsComponent(
    componentContext: ComponentContext,
    private val repository: SettingsRepository,
    private val onBack: () -> Unit,
    private val onEditProfile: () -> Unit,
) : SettingsComponent, ComponentContext by componentContext {

    private val store = SettingsStore(instanceKeeper.coroutineScope(), repository)

    override val state: StateFlow<SettingsStore.State> = store.state

    init {
        store.effect.onEach { effect ->
            when (effect) {
                is SettingsStore.Effect.NavigateBack -> onBack()
                is SettingsStore.Effect.NavigateToEditProfile -> onEditProfile()
                else -> { /* інші ефекти, напр. Toast */ }
            }
        }.launchIn(instanceKeeper.coroutineScope())
    }

    override fun onIntent(intent: SettingsStore.Intent) {
        store.onIntent(intent)
    }
}
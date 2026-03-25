package com.bbm.ataramor.features.settings.store

import com.bbm.ataramor.core.mvi.BaseStore
import com.bbm.ataramor.core.mvi.UiEffect
import com.bbm.ataramor.core.mvi.UiIntent
import com.bbm.ataramor.core.mvi.UiState
import com.bbm.ataramor.data.repository.SettingsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SettingsStore(
    scope: CoroutineScope,
    private val repository: SettingsRepository
) : BaseStore<SettingsStore.State, SettingsStore.Intent, SettingsStore.Effect>(
    initialState = State(),
    scope = scope
) {
    data class State(
        val theme: String = "System",
        val language: String = "uk",
        val isLoading: Boolean = false,
        val error: String? = null
    ) : UiState

    sealed class Intent : UiIntent {
        data class ChangeTheme(val theme: String) : Intent()
        data class ChangeLanguage(val lang: String) : Intent()
        object ClickBack : Intent()
        object ClickEditProfile : Intent()
    }

    sealed class Effect : UiEffect {
        object NavigateBack : Effect()
        object NavigateToEditProfile : Effect()
        object PlayChangeSound : Effect()
        data class ShowToast(val message: String) : Effect()
    }

    init {
        repository.settingsFlow
            .onEach { settings ->
                updateState {
                    it.copy(
                        theme = settings.theme,
                        language = settings.language,
                    )
                }
            }
            .launchIn(scope)
    }

    override fun onIntent(intent: Intent) {
        when (intent) {
            is Intent.ChangeTheme -> {
                scope.launch {
                    repository.updateTheme(intent.theme)
                    postEffect(Effect.PlayChangeSound)
                }
            }
            is Intent.ChangeLanguage -> {
                postEffect(Effect.PlayChangeSound)
            }
            is Intent.ClickBack -> {
                postEffect(Effect.NavigateBack)
            }
            is Intent.ClickEditProfile -> {
                postEffect(Effect.NavigateToEditProfile)
            }
        }
    }
}
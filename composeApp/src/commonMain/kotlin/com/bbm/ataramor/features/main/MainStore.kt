package com.bbm.ataramor.features.main

import com.bbm.ataramor.core.mvi.BaseStore
import com.bbm.ataramor.core.mvi.UiEffect
import com.bbm.ataramor.core.mvi.UiIntent
import com.bbm.ataramor.core.mvi.UiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainStore(scope: CoroutineScope) :
    BaseStore<MainStore.State, MainStore.Intent, MainStore.Effect>(
        initialState = State(),
        scope = scope
    ) {

    data class State(
        val rank: String = "20 кю",
        val unfinishedGameTitle: String? = "Незавершена гра",
        val isLoading: Boolean = false,
        val error: String? = null
    ) : UiState

    sealed class Intent : UiIntent {
        object LoadData : Intent()
        object ClickDarkWorld : Intent()
        object ClickGameWithBot : Intent()
        object ClickGameWithPlayer : Intent()
        object ClickSaveLiberty : Intent()
        object ClickDailyZen : Intent()
        object ClickFirstBlood : Intent()
        object ClickFlagCapture : Intent()
    }

    sealed class Effect : UiEffect {
        object PlayClickSound : Effect()
        data class ShowToast(val message: String) : Effect()
    }

    init {
        onIntent(Intent.LoadData)
    }

    override fun onIntent(intent: Intent) {
        when (intent) {
            is Intent.LoadData -> loadInitialDashboard()
            is Intent.ClickDarkWorld -> {
                postEffect(Effect.PlayClickSound)
            }
            is Intent.ClickGameWithBot -> {
                postEffect(Effect.PlayClickSound)
            }
            is Intent.ClickGameWithPlayer -> {
                postEffect(Effect.PlayClickSound)
            }
            is Intent.ClickSaveLiberty -> {
                postEffect(Effect.PlayClickSound)
            }
            is Intent.ClickDailyZen -> {
                postEffect(Effect.PlayClickSound)
            }
            is Intent.ClickFirstBlood -> {
                postEffect(Effect.PlayClickSound)
            }
            is Intent.ClickFlagCapture -> {
                postEffect(Effect.PlayClickSound)
            }
        }
    }

    private fun loadInitialDashboard() {
        scope.launch {
            updateState { it.copy(isLoading = true) }

            updateState {
                it.copy(
                    rank = "19 кю",
                    isLoading = false
                )
            }
        }
    }
}
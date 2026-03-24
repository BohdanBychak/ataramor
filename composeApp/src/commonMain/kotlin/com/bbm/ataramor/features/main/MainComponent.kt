package com.bbm.ataramor.features.main

import com.arkivanov.decompose.ComponentContext
import com.bbm.ataramor.core.utils.coroutineScope
import kotlinx.coroutines.flow.StateFlow

interface MainComponent{
    val state: StateFlow<MainStore.State>

    fun onIntent(intent: MainStore.Intent)

    fun onPuzzlesClick()
    fun onGameWithBotClick()
    fun onGameWithPlayerClick()
    fun onSaveLibertyClick()
    fun onDailyZenClick()
    fun onFirstBloodClick()
    fun onFlagCaptureClick()
}

class MainComponentImpl(
    componentContext: ComponentContext,
    private val onShowPuzzles: () -> Unit,
    private val onShowGameWithBot: () -> Unit,
) : MainComponent, ComponentContext by componentContext {
    private val store = MainStore(instanceKeeper.coroutineScope())

    override val state: StateFlow<MainStore.State> = store.state

    override fun onIntent(intent: MainStore.Intent) {
        store.onIntent(intent)
    }

    override fun onPuzzlesClick() {
        onShowPuzzles()
    }

    override fun onGameWithBotClick() {
        onShowGameWithBot()
    }

    override fun onGameWithPlayerClick() {
        TODO("Not yet implemented")
    }

    override fun onSaveLibertyClick() {
        TODO("Not yet implemented")
    }

    override fun onDailyZenClick() {
        TODO("Not yet implemented")
    }

    override fun onFirstBloodClick() {
        TODO("Not yet implemented")
    }

    override fun onFlagCaptureClick() {
        TODO("Not yet implemented")
    }
}
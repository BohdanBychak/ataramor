package com.bbm.ataramor.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.bbm.ataramor.features.main.MainComponent

interface RootComponent {
    val stack: Value<ChildStack<*, Child>>

    fun onMainTabClicked()
    fun onPuzzlesTabClicked()
    fun onMenuTabClicked()

    sealed class Child {
        class MainChild(val component: MainComponent) : Child()
        class PuzzlesChild() : Child()
        class MenuChild() : Child()
        class GameChild() : Child()
    }
}
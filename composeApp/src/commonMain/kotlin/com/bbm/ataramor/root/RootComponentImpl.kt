package com.bbm.ataramor.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DelicateDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.bbm.ataramor.features.main.MainComponentImpl
import kotlinx.serialization.Serializable

class RootComponentImpl(
    componentContext: ComponentContext,
) : RootComponent, ComponentContext by componentContext {
    private val navigation = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, RootComponent.Child>> =
        childStack(
            source = navigation,
            serializer = Config.serializer(),
            initialConfiguration = Config.Main,
            handleBackButton = true,
            childFactory = ::createChild,
        )

    @OptIn(DelicateDecomposeApi::class)
    private fun createChild(config: Config, context: ComponentContext): RootComponent.Child =
        when (config) {
            is Config.Main -> RootComponent.Child.MainChild(
                MainComponentImpl(
                    componentContext = context,
                    onShowPuzzles = { navigation.push(Config.Puzzles) },
                    onShowGameWithBot = { navigation.push(Config.Menu) },
                )
            )
            is Config.Menu -> RootComponent.Child.MenuChild()
            is Config.Puzzles -> RootComponent.Child.PuzzlesChild()
        }

    override fun onMainTabClicked() {
        navigation.bringToFront(Config.Main)
    }

    override fun onPuzzlesTabClicked() {
        navigation.bringToFront(Config.Puzzles)
    }

    override fun onMenuTabClicked() {
        navigation.bringToFront(Config.Menu)
    }

}
    @Serializable
    sealed interface Config {
        @Serializable object Main : Config
        @Serializable object Menu : Config
        @Serializable object Puzzles : Config
    }


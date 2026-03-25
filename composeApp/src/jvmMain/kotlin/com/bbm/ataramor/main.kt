package com.bbm.ataramor

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.bbm.ataramor.di.KoinApp
import com.bbm.ataramor.root.RootComponentImpl
import org.koin.plugin.module.dsl.startKoin

fun main() = application {
    startKoin<KoinApp>()
    val lifecycle = LifecycleRegistry()
    val root = RootComponentImpl(
        componentContext = DefaultComponentContext(lifecycle = lifecycle)
    )

    val state = rememberWindowState(
        width = 412.dp,
        height = 917.dp,
    )

    Window(
        onCloseRequest = ::exitApplication,
        title = "ataramor",
        state = state
    ) {
        App(root = root)
    }
}
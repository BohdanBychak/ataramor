package com.bbm.ataramor

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.bbm.ataramor.core.theme.AtaramorTheme
import com.bbm.ataramor.core.ui.AppBackground
import com.bbm.ataramor.core.ui.CustomFloatingBottomBar
import com.bbm.ataramor.features.main.MainContent
import com.bbm.ataramor.root.RootComponent

@Composable
fun App(root: RootComponent) {
    AtaramorTheme{
        AppBackground {
            RootContent(component = root)
        }
    }
}

@Composable
fun RootContent(component: RootComponent) {
    val childStack by component.stack.subscribeAsState()
    val activeChild = childStack.active.instance


    Scaffold(
        containerColor = Color.Transparent,
        bottomBar = {
            if (activeChild is RootComponent.Child.MainChild ||
                activeChild is RootComponent.Child.MenuChild ||
                activeChild is RootComponent.Child.PuzzlesChild) {
                CustomFloatingBottomBar(component)
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            Children(
                stack = component.stack,
                animation = stackAnimation(fade())
            ) {
                when (val child = it.instance) {
                    is RootComponent.Child.MainChild -> MainContent(child.component)
                    is RootComponent.Child.MenuChild -> DetailsContent()
                    is RootComponent.Child.PuzzlesChild -> DetailsContent()
                    is RootComponent.Child.GameChild -> DetailsContent()
                }
            }
        }
    }
}

@Composable
fun DetailsContent() {
    Text(text = "Details")
}
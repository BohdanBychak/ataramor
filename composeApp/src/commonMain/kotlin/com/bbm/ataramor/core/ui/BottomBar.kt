package com.bbm.ataramor.core.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ataramor.composeapp.generated.resources.Res
import ataramor.composeapp.generated.resources.book_icon
import ataramor.composeapp.generated.resources.chart_icon
import ataramor.composeapp.generated.resources.home_icon
import ataramor.composeapp.generated.resources.menu_icon
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.bbm.ataramor.root.Config
import com.bbm.ataramor.root.RootComponent
import com.bbm.ataramor.root.RootComponentImpl
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import java.io.ObjectInputFilter

@Composable
fun CustomFloatingBottomBar(component: RootComponent) {
    val childStack by component.stack.subscribeAsState()
    val activeConfig = childStack.active.configuration

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Surface(
            modifier = Modifier
                .height(64.dp)
                .fillMaxWidth(),
            shape = CircleShape,
            color = Color.White.copy(alpha = 0.4f),
            border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.5f)),
            shadowElevation = 8.dp
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                NavigationItem(Res.drawable.home_icon, isSelected = activeConfig is Config.Main, onClick = { component.onMainTabClicked() })
                NavigationItem(Res.drawable.book_icon, isSelected = activeConfig is Config.Puzzles, onClick = { component.onPuzzlesTabClicked() })
                NavigationItem(Res.drawable.chart_icon)
                NavigationItem(Res.drawable.menu_icon, isSelected = activeConfig is Config.Menu, onClick = { component.onMenuTabClicked() })
            }
        }
    }
}

@Composable
fun NavigationItem(iconRes: DrawableResource, isSelected: Boolean = false, onClick: () -> Unit = {}) {
    IconButton(onClick = {
        onClick()
    }) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = null,
            tint = if (isSelected) Color(0xFF8B5E3C) else Color.Black,
            modifier = Modifier.size(28.dp)
        )
    }
}
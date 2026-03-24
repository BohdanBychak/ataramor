package com.bbm.ataramor.features.main

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ataramor.composeapp.generated.resources.Res
import ataramor.composeapp.generated.resources.game_with_bot
import ataramor.composeapp.generated.resources.game_with_player
import org.jetbrains.compose.resources.painterResource

@Composable
fun MainContent(component: MainComponent) {
    val state by component.state.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        color = Color.Transparent
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("ATARAMOR", style = MaterialTheme.typography.headlineLarge)

            Row(
                modifier = Modifier
                    .height(200.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    ChoosingGameModeCard(
                        modifier = Modifier.fillMaxHeight(),
                        title = "ДВОБІЙ З БОТОМ",
                        description = "9x9/13x13/19x19",
                        painter = painterResource(Res.drawable.game_with_bot),
                    ) { component.onGameWithBotClick() }
                }
                Box(modifier = Modifier.weight(1f)) {
                    ChoosingGameModeCard(
                        modifier = Modifier.fillMaxHeight(),
                        title = "ВИКЛИК ГРАВЦЮ",
                        description = "Локальний або онлайн",
                        painter = painterResource(Res.drawable.game_with_player),
                        isEnabled = false,
                    ) { component.onGameWithPlayerClick() }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ChoosingGameModeCard(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    painter: Painter,
    isEnabled: Boolean = true,
    onClick: () -> Unit = { },
) {
    val containerColor =
        if (!isEnabled) MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f) else MaterialTheme.colorScheme.primaryContainer
    val contentColor =
        if (!isEnabled) MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f) else MaterialTheme.colorScheme.onPrimaryContainer
    val borderColor =
        if (!isEnabled) MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f) else MaterialTheme.colorScheme.primary

    val colorFilter = if (!isEnabled) {
        ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) })
    } else null

    Surface(
        onClick = onClick,
        enabled = isEnabled,
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        color = containerColor,
        contentColor = contentColor,
        border = BorderStroke(2.dp, borderColor)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painter,
                contentDescription = null,
                colorFilter = colorFilter,
                contentScale = ContentScale.Fit
            )
            Text(
                title,
                style = MaterialTheme.typography.titleMediumEmphasized,
                color = contentColor,
                textAlign = TextAlign.Center
            )
            Text(
                description,
                style = MaterialTheme.typography.labelSmall,
                color = contentColor,
                textAlign = TextAlign.Center
            )
        }
    }
}
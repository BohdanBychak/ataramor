package com.bbm.ataramor.core.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val AtaraGold = Color(0xFFC19A6B)
val ZenWhite = Color(0xFFF5F5F0)
val InkBlack = Color(0xFF1A1A1A)
val MorokDeepBlue = Color(0xFF0F172A)
val MorokMist = Color(0xFF334155).copy(alpha = 0.6f)

val PrimaryContainer = Color(0xFFF2E6D8)
val OnPrimaryContainer = Color(0xFF322818)

val OnAtaraGold = Color(0xFFFFFFFF)
val ParchmentBackground = Color(0xFFFAF9F6)
val InkText = Color(0xFF232323)
val PebbleVariant = Color(0xFFEBE9E1)
val MossStone = Color(0xFF5D624E)
val StoneOutline = Color(0xFFD1CDC7)
val ContainerVariant = Color(0xF5FFF6EB)

// Світла схема (Ранковий сад)
val LightColorScheme = lightColorScheme(
    primary = AtaraGold,
    background = ZenWhite,
    surface = Color.White,
    onBackground = InkBlack,
    onSurface = InkBlack,
    onPrimary = OnAtaraGold,
    primaryContainer = Color(0xFFF2E6D8),
    secondary = MossStone,
    surfaceVariant = PebbleVariant,
    outline = StoneOutline,
    secondaryContainer = ContainerVariant,
)

// Темна схема (Морок)
val DarkColorScheme = darkColorScheme(
    primary = AtaraGold,
    background = MorokDeepBlue,
    surface = Color(0xFF1E293B),
    onBackground = Color.White,
    onSurface = Color.White
)
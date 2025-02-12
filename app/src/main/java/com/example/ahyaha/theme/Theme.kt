package com.example.ahyaha.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = PulseRed,
    secondary = VitalOrange,
    tertiary = SerenityBlue,
    background = SoftWhite,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = CoolGray,
    onSurface = CoolGray,
)

private val DarkColorScheme = darkColorScheme(
    primary = DeepUrgencyRed,
    secondary = IntenseCrimson,
    tertiary = MidnightBlue,
    background = Color(0xFF101820), // Dark modern blue-black
    surface = Color(0xFF1C1F26), // Soft dark mode surface
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color.White.copy(alpha = 0.9f),
    onSurface = Color.White.copy(alpha = 0.85f),
)

@Composable
fun AhyahaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val colors = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val activity = context as? Activity
            if (darkTheme) dynamicDarkColorScheme(activity!!) else dynamicLightColorScheme(activity!!)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}

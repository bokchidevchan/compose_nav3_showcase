package io.github.bokchidevchan.compose_nav3_showcase.core.data.model

data class AppSettings(
    val themeMode: ThemeMode = ThemeMode.SYSTEM,
    val dynamicColorsEnabled: Boolean = true,
    val highContrastEnabled: Boolean = false,
    val developerModeEnabled: Boolean = false,
    val loggingEnabled: Boolean = false,
    val debugOverlaysEnabled: Boolean = false
)

package io.github.bokchidevchan.compose_nav3_showcase.ui.screens.display

import io.github.bokchidevchan.compose_nav3_showcase.data.model.ThemeMode
import io.github.bokchidevchan.compose_nav3_showcase.ui.base.MviEffect
import io.github.bokchidevchan.compose_nav3_showcase.ui.base.MviIntent
import io.github.bokchidevchan.compose_nav3_showcase.ui.base.MviState

data class DisplayState(
    val themeMode: ThemeMode = ThemeMode.SYSTEM,
    val dynamicColorsEnabled: Boolean = true,
    val highContrastEnabled: Boolean = false,
    val isLoading: Boolean = true
) : MviState

sealed interface DisplayIntent : MviIntent {
    data object LoadSettings : DisplayIntent
    data class SetDynamicColors(val enabled: Boolean) : DisplayIntent
    data class SetHighContrast(val enabled: Boolean) : DisplayIntent
    data object NavigateToThemeSelection : DisplayIntent
}

sealed interface DisplayEffect : MviEffect {
    data object NavigateToThemeSelection : DisplayEffect
}

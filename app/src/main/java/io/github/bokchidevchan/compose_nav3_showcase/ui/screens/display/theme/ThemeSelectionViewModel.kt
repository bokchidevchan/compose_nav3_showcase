package io.github.bokchidevchan.compose_nav3_showcase.ui.screens.display.theme

import androidx.lifecycle.viewModelScope
import io.github.bokchidevchan.compose_nav3_showcase.ComposeNav3ShowcaseApp
import io.github.bokchidevchan.compose_nav3_showcase.data.model.ThemeMode
import io.github.bokchidevchan.compose_nav3_showcase.ui.base.BaseViewModel
import io.github.bokchidevchan.compose_nav3_showcase.ui.base.MviEffect
import io.github.bokchidevchan.compose_nav3_showcase.ui.base.MviIntent
import io.github.bokchidevchan.compose_nav3_showcase.ui.base.MviState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

data class ThemeSelectionState(
    val selectedTheme: ThemeMode = ThemeMode.SYSTEM,
    val isLoading: Boolean = true
) : MviState

sealed interface ThemeSelectionIntent : MviIntent {
    data class SelectTheme(val mode: ThemeMode) : ThemeSelectionIntent
}

sealed interface ThemeSelectionEffect : MviEffect

class ThemeSelectionViewModel : BaseViewModel<ThemeSelectionState, ThemeSelectionIntent, ThemeSelectionEffect>(
    ThemeSelectionState()
) {
    private val repository = ComposeNav3ShowcaseApp.instance.settingsRepository

    init {
        repository.getAppSettings()
            .onEach { settings ->
                updateState {
                    copy(selectedTheme = settings.themeMode, isLoading = false)
                }
            }
            .launchIn(viewModelScope)
    }

    override fun processIntent(intent: ThemeSelectionIntent) {
        when (intent) {
            is ThemeSelectionIntent.SelectTheme -> selectTheme(intent.mode)
        }
    }

    private fun selectTheme(mode: ThemeMode) {
        viewModelScope.launch {
            repository.setThemeMode(mode)
        }
    }
}

package io.github.bokchidevchan.compose_nav3_showcase.feature.display.theme

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.bokchidevchan.compose_nav3_showcase.core.data.model.ThemeMode
import io.github.bokchidevchan.compose_nav3_showcase.core.data.repository.SettingsRepository
import io.github.bokchidevchan.compose_nav3_showcase.core.ui.base.BaseViewModel
import io.github.bokchidevchan.compose_nav3_showcase.core.ui.base.MviEffect
import io.github.bokchidevchan.compose_nav3_showcase.core.ui.base.MviIntent
import io.github.bokchidevchan.compose_nav3_showcase.core.ui.base.MviState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ThemeSelectionState(
    val selectedTheme: ThemeMode = ThemeMode.SYSTEM,
    val isLoading: Boolean = true
) : MviState

sealed interface ThemeSelectionIntent : MviIntent {
    data class SelectTheme(val mode: ThemeMode) : ThemeSelectionIntent
}

sealed interface ThemeSelectionEffect : MviEffect

@HiltViewModel
class ThemeSelectionViewModel @Inject constructor(
    private val repository: SettingsRepository
) : BaseViewModel<ThemeSelectionState, ThemeSelectionIntent, ThemeSelectionEffect>(
    ThemeSelectionState()
) {

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

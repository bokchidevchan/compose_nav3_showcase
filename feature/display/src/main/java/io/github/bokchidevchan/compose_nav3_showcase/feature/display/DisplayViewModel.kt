package io.github.bokchidevchan.compose_nav3_showcase.feature.display

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.bokchidevchan.compose_nav3_showcase.core.data.repository.SettingsRepository
import io.github.bokchidevchan.compose_nav3_showcase.core.ui.base.BaseViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DisplayViewModel @Inject constructor(
    private val repository: SettingsRepository
) : BaseViewModel<DisplayState, DisplayIntent, DisplayEffect>(DisplayState()) {

    init {
        observeSettings()
    }

    private fun observeSettings() {
        repository.getAppSettings()
            .onEach { settings ->
                updateState {
                    copy(
                        themeMode = settings.themeMode,
                        dynamicColorsEnabled = settings.dynamicColorsEnabled,
                        highContrastEnabled = settings.highContrastEnabled,
                        isLoading = false
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    override fun processIntent(intent: DisplayIntent) {
        when (intent) {
            DisplayIntent.LoadSettings -> { /* Already observing */ }
            is DisplayIntent.SetDynamicColors -> setDynamicColors(intent.enabled)
            is DisplayIntent.SetHighContrast -> setHighContrast(intent.enabled)
            DisplayIntent.NavigateToThemeSelection -> sendEffect(DisplayEffect.NavigateToThemeSelection)
        }
    }

    private fun setDynamicColors(enabled: Boolean) {
        viewModelScope.launch {
            repository.setDynamicColors(enabled)
        }
    }

    private fun setHighContrast(enabled: Boolean) {
        viewModelScope.launch {
            repository.setHighContrast(enabled)
        }
    }
}

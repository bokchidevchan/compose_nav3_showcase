package io.github.bokchidevchan.compose_nav3_showcase.ui.screens.display

import androidx.lifecycle.viewModelScope
import io.github.bokchidevchan.compose_nav3_showcase.ComposeNav3ShowcaseApp
import io.github.bokchidevchan.compose_nav3_showcase.ui.base.BaseViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class DisplayViewModel : BaseViewModel<DisplayState, DisplayIntent, DisplayEffect>(DisplayState()) {

    private val repository = ComposeNav3ShowcaseApp.instance.settingsRepository

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

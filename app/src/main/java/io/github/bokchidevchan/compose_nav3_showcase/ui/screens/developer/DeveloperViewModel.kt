package io.github.bokchidevchan.compose_nav3_showcase.ui.screens.developer

import androidx.lifecycle.viewModelScope
import io.github.bokchidevchan.compose_nav3_showcase.ComposeNav3ShowcaseApp
import io.github.bokchidevchan.compose_nav3_showcase.ui.base.BaseViewModel
import io.github.bokchidevchan.compose_nav3_showcase.ui.base.MviEffect
import io.github.bokchidevchan.compose_nav3_showcase.ui.base.MviIntent
import io.github.bokchidevchan.compose_nav3_showcase.ui.base.MviState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

data class DeveloperState(
    val loggingEnabled: Boolean = false,
    val debugOverlaysEnabled: Boolean = false,
    val isLoading: Boolean = true
) : MviState

sealed interface DeveloperIntent : MviIntent {
    data class SetLogging(val enabled: Boolean) : DeveloperIntent
    data class SetDebugOverlays(val enabled: Boolean) : DeveloperIntent
}

sealed interface DeveloperEffect : MviEffect

class DeveloperViewModel : BaseViewModel<DeveloperState, DeveloperIntent, DeveloperEffect>(
    DeveloperState()
) {
    private val repository = ComposeNav3ShowcaseApp.instance.settingsRepository

    init {
        repository.getAppSettings()
            .onEach { settings ->
                updateState {
                    copy(
                        loggingEnabled = settings.loggingEnabled,
                        debugOverlaysEnabled = settings.debugOverlaysEnabled,
                        isLoading = false
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    override fun processIntent(intent: DeveloperIntent) {
        when (intent) {
            is DeveloperIntent.SetLogging -> setLogging(intent.enabled)
            is DeveloperIntent.SetDebugOverlays -> setDebugOverlays(intent.enabled)
        }
    }

    private fun setLogging(enabled: Boolean) {
        viewModelScope.launch {
            repository.setLogging(enabled)
        }
    }

    private fun setDebugOverlays(enabled: Boolean) {
        viewModelScope.launch {
            repository.setDebugOverlays(enabled)
        }
    }
}

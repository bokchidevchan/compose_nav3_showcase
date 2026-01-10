package io.github.bokchidevchan.compose_nav3_showcase.ui.screens.storage

import androidx.lifecycle.viewModelScope
import io.github.bokchidevchan.compose_nav3_showcase.ComposeNav3ShowcaseApp
import io.github.bokchidevchan.compose_nav3_showcase.ui.base.BaseViewModel
import io.github.bokchidevchan.compose_nav3_showcase.ui.base.MviEffect
import io.github.bokchidevchan.compose_nav3_showcase.ui.base.MviIntent
import io.github.bokchidevchan.compose_nav3_showcase.ui.base.MviState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

data class StorageState(
    val totalSize: Long = 0,
    val cacheSize: Long = 0,
    val dataSize: Long = 0,
    val logsSize: Long = 0,
    val isLoading: Boolean = true
) : MviState

sealed interface StorageIntent : MviIntent {
    data object ClearCache : StorageIntent
    data object ClearAllData : StorageIntent
}

sealed interface StorageEffect : MviEffect {
    data object DataCleared : StorageEffect
}

class StorageViewModel : BaseViewModel<StorageState, StorageIntent, StorageEffect>(StorageState()) {

    private val repository = ComposeNav3ShowcaseApp.instance.settingsRepository

    init {
        observeStorageSize()
    }

    private fun observeStorageSize() {
        repository.getTotalStorageSize()
            .onEach { size ->
                updateState { copy(totalSize = size ?: 0, isLoading = false) }
            }
            .launchIn(viewModelScope)

        repository.getStorageSizeByCategory("cache")
            .onEach { size ->
                updateState { copy(cacheSize = size ?: 0) }
            }
            .launchIn(viewModelScope)

        repository.getStorageSizeByCategory("data")
            .onEach { size ->
                updateState { copy(dataSize = size ?: 0) }
            }
            .launchIn(viewModelScope)

        repository.getStorageSizeByCategory("logs")
            .onEach { size ->
                updateState { copy(logsSize = size ?: 0) }
            }
            .launchIn(viewModelScope)
    }

    override fun processIntent(intent: StorageIntent) {
        when (intent) {
            StorageIntent.ClearCache -> clearCache()
            StorageIntent.ClearAllData -> clearAllData()
        }
    }

    private fun clearCache() {
        viewModelScope.launch {
            repository.deleteStorageByCategory("cache")
        }
    }

    private fun clearAllData() {
        viewModelScope.launch {
            repository.clearAllStorage()
            sendEffect(StorageEffect.DataCleared)
        }
    }
}

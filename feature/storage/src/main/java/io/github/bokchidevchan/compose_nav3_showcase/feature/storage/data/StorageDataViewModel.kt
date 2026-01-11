package io.github.bokchidevchan.compose_nav3_showcase.feature.storage.data

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.bokchidevchan.compose_nav3_showcase.core.data.database.entity.StorageItemEntity
import io.github.bokchidevchan.compose_nav3_showcase.core.data.repository.SettingsRepository
import io.github.bokchidevchan.compose_nav3_showcase.core.ui.base.BaseViewModel
import io.github.bokchidevchan.compose_nav3_showcase.core.ui.base.MviEffect
import io.github.bokchidevchan.compose_nav3_showcase.core.ui.base.MviIntent
import io.github.bokchidevchan.compose_nav3_showcase.core.ui.base.MviState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

data class StorageDataState(
    val items: List<StorageItemEntity> = emptyList(),
    val isLoading: Boolean = true
) : MviState

sealed interface StorageDataIntent : MviIntent {
    data class DeleteItem(val id: Long) : StorageDataIntent
}

sealed interface StorageDataEffect : MviEffect

@HiltViewModel
class StorageDataViewModel @Inject constructor(
    private val repository: SettingsRepository
) : BaseViewModel<StorageDataState, StorageDataIntent, StorageDataEffect>(
    StorageDataState()
) {
    init {
        repository.getAllStorageItems()
            .onEach { items ->
                updateState { copy(items = items, isLoading = false) }
            }
            .launchIn(viewModelScope)
    }

    override fun processIntent(intent: StorageDataIntent) {
        when (intent) {
            is StorageDataIntent.DeleteItem -> deleteItem(intent.id)
        }
    }

    private fun deleteItem(id: Long) {
        viewModelScope.launch {
            repository.deleteStorageItem(id)
        }
    }
}

package io.github.bokchidevchan.compose_nav3_showcase.ui.screens.storage.data

import androidx.lifecycle.viewModelScope
import io.github.bokchidevchan.compose_nav3_showcase.ComposeNav3ShowcaseApp
import io.github.bokchidevchan.compose_nav3_showcase.data.database.entity.StorageItemEntity
import io.github.bokchidevchan.compose_nav3_showcase.ui.base.BaseViewModel
import io.github.bokchidevchan.compose_nav3_showcase.ui.base.MviEffect
import io.github.bokchidevchan.compose_nav3_showcase.ui.base.MviIntent
import io.github.bokchidevchan.compose_nav3_showcase.ui.base.MviState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

data class StorageDataState(
    val items: List<StorageItemEntity> = emptyList(),
    val isLoading: Boolean = true
) : MviState

sealed interface StorageDataIntent : MviIntent {
    data class DeleteItem(val id: Long) : StorageDataIntent
}

sealed interface StorageDataEffect : MviEffect

class StorageDataViewModel : BaseViewModel<StorageDataState, StorageDataIntent, StorageDataEffect>(
    StorageDataState()
) {
    private val repository = ComposeNav3ShowcaseApp.instance.settingsRepository

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

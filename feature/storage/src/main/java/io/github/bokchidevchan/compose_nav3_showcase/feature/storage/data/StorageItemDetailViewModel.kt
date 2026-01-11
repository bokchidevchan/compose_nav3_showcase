package io.github.bokchidevchan.compose_nav3_showcase.feature.storage.data

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.bokchidevchan.compose_nav3_showcase.core.data.database.entity.StorageItemEntity
import io.github.bokchidevchan.compose_nav3_showcase.core.data.repository.SettingsRepository
import io.github.bokchidevchan.compose_nav3_showcase.core.ui.base.BaseViewModel
import io.github.bokchidevchan.compose_nav3_showcase.core.ui.base.MviEffect
import io.github.bokchidevchan.compose_nav3_showcase.core.ui.base.MviIntent
import io.github.bokchidevchan.compose_nav3_showcase.core.ui.base.MviState
import kotlinx.coroutines.launch
import javax.inject.Inject

data class StorageItemDetailState(
    val item: StorageItemEntity? = null,
    val isLoading: Boolean = true
) : MviState

sealed interface StorageItemDetailIntent : MviIntent {
    data class LoadItem(val id: Long) : StorageItemDetailIntent
    data object DeleteItem : StorageItemDetailIntent
}

sealed interface StorageItemDetailEffect : MviEffect {
    data object ItemDeleted : StorageItemDetailEffect
}

@HiltViewModel
class StorageItemDetailViewModel @Inject constructor(
    private val repository: SettingsRepository
) : BaseViewModel<StorageItemDetailState, StorageItemDetailIntent, StorageItemDetailEffect>(
    StorageItemDetailState()
) {
    override fun processIntent(intent: StorageItemDetailIntent) {
        when (intent) {
            is StorageItemDetailIntent.LoadItem -> loadItem(intent.id)
            StorageItemDetailIntent.DeleteItem -> deleteItem()
        }
    }

    private fun loadItem(id: Long) {
        viewModelScope.launch {
            val item = repository.getStorageItemById(id)
            updateState { copy(item = item, isLoading = false) }
        }
    }

    private fun deleteItem() {
        state.value.item?.let { item ->
            viewModelScope.launch {
                repository.deleteStorageItem(item.id)
                sendEffect(StorageItemDetailEffect.ItemDeleted)
            }
        }
    }
}

package io.github.bokchidevchan.compose_nav3_showcase.ui.screens.storage.data

import androidx.lifecycle.viewModelScope
import io.github.bokchidevchan.compose_nav3_showcase.ComposeNav3ShowcaseApp
import io.github.bokchidevchan.compose_nav3_showcase.data.database.entity.StorageItemEntity
import io.github.bokchidevchan.compose_nav3_showcase.ui.base.BaseViewModel
import io.github.bokchidevchan.compose_nav3_showcase.ui.base.MviEffect
import io.github.bokchidevchan.compose_nav3_showcase.ui.base.MviIntent
import io.github.bokchidevchan.compose_nav3_showcase.ui.base.MviState
import kotlinx.coroutines.launch

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

class StorageItemDetailViewModel : BaseViewModel<StorageItemDetailState, StorageItemDetailIntent, StorageItemDetailEffect>(
    StorageItemDetailState()
) {
    private val repository = ComposeNav3ShowcaseApp.instance.settingsRepository

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

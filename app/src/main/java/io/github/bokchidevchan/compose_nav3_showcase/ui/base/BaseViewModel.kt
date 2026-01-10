package io.github.bokchidevchan.compose_nav3_showcase.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<S : MviState, I : MviIntent, E : MviEffect>(
    initialState: S
) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state.asStateFlow()

    private val _effects = Channel<E>(Channel.BUFFERED)
    val effects = _effects.receiveAsFlow()

    protected fun updateState(reducer: S.() -> S) {
        _state.value = _state.value.reducer()
    }

    protected fun sendEffect(effect: E) {
        viewModelScope.launch {
            _effects.send(effect)
        }
    }

    abstract fun processIntent(intent: I)
}

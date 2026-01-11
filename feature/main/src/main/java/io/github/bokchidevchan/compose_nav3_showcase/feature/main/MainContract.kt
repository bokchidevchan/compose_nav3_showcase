package io.github.bokchidevchan.compose_nav3_showcase.feature.main

import io.github.bokchidevchan.compose_nav3_showcase.core.ui.base.MviEffect
import io.github.bokchidevchan.compose_nav3_showcase.core.ui.base.MviIntent
import io.github.bokchidevchan.compose_nav3_showcase.core.ui.base.MviState

data class MainState(
    val isLoading: Boolean = false
) : MviState

sealed interface MainIntent : MviIntent {
    data object NavigateToDisplay : MainIntent
    data object NavigateToStorage : MainIntent
    data object NavigateToAbout : MainIntent
    data object NavigateToDeveloper : MainIntent
}

sealed interface MainEffect : MviEffect {
    data object NavigateToDisplay : MainEffect
    data object NavigateToStorage : MainEffect
    data object NavigateToAbout : MainEffect
    data object NavigateToDeveloper : MainEffect
}

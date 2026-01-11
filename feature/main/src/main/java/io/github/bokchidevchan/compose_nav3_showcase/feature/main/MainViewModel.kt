package io.github.bokchidevchan.compose_nav3_showcase.feature.main

import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.bokchidevchan.compose_nav3_showcase.core.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel<MainState, MainIntent, MainEffect>(MainState()) {

    override fun processIntent(intent: MainIntent) {
        when (intent) {
            MainIntent.NavigateToDisplay -> sendEffect(MainEffect.NavigateToDisplay)
            MainIntent.NavigateToStorage -> sendEffect(MainEffect.NavigateToStorage)
            MainIntent.NavigateToAbout -> sendEffect(MainEffect.NavigateToAbout)
            MainIntent.NavigateToDeveloper -> sendEffect(MainEffect.NavigateToDeveloper)
        }
    }
}

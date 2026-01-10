package io.github.bokchidevchan.compose_nav3_showcase.ui.screens.main

import io.github.bokchidevchan.compose_nav3_showcase.ui.base.BaseViewModel

class MainViewModel : BaseViewModel<MainState, MainIntent, MainEffect>(MainState()) {

    override fun processIntent(intent: MainIntent) {
        when (intent) {
            MainIntent.NavigateToDisplay -> sendEffect(MainEffect.NavigateToDisplay)
            MainIntent.NavigateToStorage -> sendEffect(MainEffect.NavigateToStorage)
            MainIntent.NavigateToAbout -> sendEffect(MainEffect.NavigateToAbout)
            MainIntent.NavigateToDeveloper -> sendEffect(MainEffect.NavigateToDeveloper)
        }
    }
}

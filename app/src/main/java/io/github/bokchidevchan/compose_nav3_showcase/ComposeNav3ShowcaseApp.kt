package io.github.bokchidevchan.compose_nav3_showcase

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.github.bokchidevchan.compose_nav3_showcase.core.data.repository.SettingsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class ComposeNav3ShowcaseApp : Application() {

    @Inject
    lateinit var settingsRepository: SettingsRepository

    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onCreate() {
        super.onCreate()
        initializeSampleDataIfNeeded()
    }

    private fun initializeSampleDataIfNeeded() {
        applicationScope.launch {
            settingsRepository.initializeSampleData()
        }
    }
}

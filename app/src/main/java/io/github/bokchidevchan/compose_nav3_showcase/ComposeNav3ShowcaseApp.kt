package io.github.bokchidevchan.compose_nav3_showcase

import android.app.Application
import io.github.bokchidevchan.compose_nav3_showcase.data.database.AppDatabase
import io.github.bokchidevchan.compose_nav3_showcase.data.repository.SettingsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class ComposeNav3ShowcaseApp : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }

    val settingsRepository: SettingsRepository by lazy {
        SettingsRepository(database.settingsDao(), database.storageDao())
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initializeSampleDataIfNeeded()
    }

    private fun initializeSampleDataIfNeeded() {
        applicationScope.launch {
            val items = database.storageDao().getItemById(1)
            if (items == null) {
                settingsRepository.initializeSampleData()
            }
        }
    }

    companion object {
        lateinit var instance: ComposeNav3ShowcaseApp
            private set
    }
}

package io.github.bokchidevchan.compose_nav3_showcase.core.data.repository

import io.github.bokchidevchan.compose_nav3_showcase.core.data.database.dao.SettingsDao
import io.github.bokchidevchan.compose_nav3_showcase.core.data.database.dao.StorageDao
import io.github.bokchidevchan.compose_nav3_showcase.core.data.database.entity.SettingEntity
import io.github.bokchidevchan.compose_nav3_showcase.core.data.database.entity.StorageItemEntity
import io.github.bokchidevchan.compose_nav3_showcase.core.data.model.AppSettings
import io.github.bokchidevchan.compose_nav3_showcase.core.data.model.ThemeMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsRepository @Inject constructor(
    private val settingsDao: SettingsDao,
    private val storageDao: StorageDao
) {
    companion object {
        private const val KEY_THEME_MODE = "theme_mode"
        private const val KEY_DYNAMIC_COLORS = "dynamic_colors"
        private const val KEY_HIGH_CONTRAST = "high_contrast"
        private const val KEY_DEVELOPER_MODE = "developer_mode"
        private const val KEY_LOGGING = "logging"
        private const val KEY_DEBUG_OVERLAYS = "debug_overlays"
    }

    fun getAppSettings(): Flow<AppSettings> {
        return settingsDao.getAllSettings().map { settings ->
            val settingsMap = settings.associateBy { it.key }
            AppSettings(
                themeMode = settingsMap[KEY_THEME_MODE]?.value?.let {
                    ThemeMode.valueOf(it)
                } ?: ThemeMode.SYSTEM,
                dynamicColorsEnabled = settingsMap[KEY_DYNAMIC_COLORS]?.value?.toBooleanStrictOrNull() ?: true,
                highContrastEnabled = settingsMap[KEY_HIGH_CONTRAST]?.value?.toBooleanStrictOrNull() ?: false,
                developerModeEnabled = settingsMap[KEY_DEVELOPER_MODE]?.value?.toBooleanStrictOrNull() ?: false,
                loggingEnabled = settingsMap[KEY_LOGGING]?.value?.toBooleanStrictOrNull() ?: false,
                debugOverlaysEnabled = settingsMap[KEY_DEBUG_OVERLAYS]?.value?.toBooleanStrictOrNull() ?: false
            )
        }
    }

    suspend fun setThemeMode(mode: ThemeMode) {
        settingsDao.insertSetting(SettingEntity(KEY_THEME_MODE, mode.name))
    }

    suspend fun setDynamicColors(enabled: Boolean) {
        settingsDao.insertSetting(SettingEntity(KEY_DYNAMIC_COLORS, enabled.toString()))
    }

    suspend fun setHighContrast(enabled: Boolean) {
        settingsDao.insertSetting(SettingEntity(KEY_HIGH_CONTRAST, enabled.toString()))
    }

    suspend fun setDeveloperMode(enabled: Boolean) {
        settingsDao.insertSetting(SettingEntity(KEY_DEVELOPER_MODE, enabled.toString()))
    }

    suspend fun setLogging(enabled: Boolean) {
        settingsDao.insertSetting(SettingEntity(KEY_LOGGING, enabled.toString()))
    }

    suspend fun setDebugOverlays(enabled: Boolean) {
        settingsDao.insertSetting(SettingEntity(KEY_DEBUG_OVERLAYS, enabled.toString()))
    }

    // Storage operations
    fun getAllStorageItems(): Flow<List<StorageItemEntity>> = storageDao.getAllItems()

    fun getStorageItemsByCategory(category: String): Flow<List<StorageItemEntity>> =
        storageDao.getItemsByCategory(category)

    suspend fun getStorageItemById(id: Long): StorageItemEntity? = storageDao.getItemById(id)

    fun getTotalStorageSize(): Flow<Long?> = storageDao.getTotalSize()

    fun getStorageSizeByCategory(category: String): Flow<Long?> = storageDao.getSizeByCategory(category)

    suspend fun insertStorageItem(item: StorageItemEntity): Long = storageDao.insertItem(item)

    suspend fun insertStorageItems(items: List<StorageItemEntity>) = storageDao.insertItems(items)

    suspend fun deleteStorageItem(id: Long) = storageDao.deleteItemById(id)

    suspend fun deleteStorageByCategory(category: String) = storageDao.deleteByCategory(category)

    suspend fun clearAllStorage() = storageDao.deleteAll()

    suspend fun initializeSampleData() {
        val sampleItems = listOf(
            StorageItemEntity(name = "Image Cache", description = "Cached images from network", sizeBytes = 15_000_000, category = "cache"),
            StorageItemEntity(name = "Network Cache", description = "API response cache", sizeBytes = 5_000_000, category = "cache"),
            StorageItemEntity(name = "User Preferences", description = "App settings and preferences", sizeBytes = 50_000, category = "data"),
            StorageItemEntity(name = "Database", description = "Local database files", sizeBytes = 2_000_000, category = "data"),
            StorageItemEntity(name = "Debug Logs", description = "Application debug logs", sizeBytes = 1_500_000, category = "logs"),
            StorageItemEntity(name = "Crash Reports", description = "Crash report files", sizeBytes = 500_000, category = "logs")
        )
        storageDao.insertItems(sampleItems)
    }
}

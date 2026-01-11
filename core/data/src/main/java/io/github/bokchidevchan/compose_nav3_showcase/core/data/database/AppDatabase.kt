package io.github.bokchidevchan.compose_nav3_showcase.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.bokchidevchan.compose_nav3_showcase.core.data.database.dao.SettingsDao
import io.github.bokchidevchan.compose_nav3_showcase.core.data.database.dao.StorageDao
import io.github.bokchidevchan.compose_nav3_showcase.core.data.database.entity.SettingEntity
import io.github.bokchidevchan.compose_nav3_showcase.core.data.database.entity.StorageItemEntity

@Database(
    entities = [SettingEntity::class, StorageItemEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun settingsDao(): SettingsDao
    abstract fun storageDao(): StorageDao
}

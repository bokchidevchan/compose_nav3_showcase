package io.github.bokchidevchan.compose_nav3_showcase.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.bokchidevchan.compose_nav3_showcase.data.database.dao.SettingsDao
import io.github.bokchidevchan.compose_nav3_showcase.data.database.dao.StorageDao
import io.github.bokchidevchan.compose_nav3_showcase.data.database.entity.SettingEntity
import io.github.bokchidevchan.compose_nav3_showcase.data.database.entity.StorageItemEntity

@Database(
    entities = [SettingEntity::class, StorageItemEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun settingsDao(): SettingsDao
    abstract fun storageDao(): StorageDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "compose_nav3_showcase.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

package io.github.bokchidevchan.compose_nav3_showcase.core.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.bokchidevchan.compose_nav3_showcase.core.data.database.AppDatabase
import io.github.bokchidevchan.compose_nav3_showcase.core.data.database.dao.SettingsDao
import io.github.bokchidevchan.compose_nav3_showcase.core.data.database.dao.StorageDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "compose_nav3_showcase.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideSettingsDao(database: AppDatabase): SettingsDao {
        return database.settingsDao()
    }

    @Provides
    @Singleton
    fun provideStorageDao(database: AppDatabase): StorageDao {
        return database.storageDao()
    }
}

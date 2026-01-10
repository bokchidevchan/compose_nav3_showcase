package io.github.bokchidevchan.compose_nav3_showcase.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.bokchidevchan.compose_nav3_showcase.data.database.entity.SettingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingsDao {

    @Query("SELECT * FROM settings")
    fun getAllSettings(): Flow<List<SettingEntity>>

    @Query("SELECT * FROM settings WHERE key = :key")
    fun getSettingByKey(key: String): Flow<SettingEntity?>

    @Query("SELECT value FROM settings WHERE key = :key")
    suspend fun getSettingValue(key: String): String?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSetting(setting: SettingEntity)

    @Query("DELETE FROM settings WHERE key = :key")
    suspend fun deleteSetting(key: String)

    @Query("DELETE FROM settings")
    suspend fun deleteAllSettings()
}

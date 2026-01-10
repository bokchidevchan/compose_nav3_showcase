package io.github.bokchidevchan.compose_nav3_showcase.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.github.bokchidevchan.compose_nav3_showcase.data.database.entity.StorageItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StorageDao {

    @Query("SELECT * FROM storage_items ORDER BY createdAt DESC")
    fun getAllItems(): Flow<List<StorageItemEntity>>

    @Query("SELECT * FROM storage_items WHERE category = :category ORDER BY createdAt DESC")
    fun getItemsByCategory(category: String): Flow<List<StorageItemEntity>>

    @Query("SELECT * FROM storage_items WHERE id = :id")
    suspend fun getItemById(id: Long): StorageItemEntity?

    @Query("SELECT SUM(sizeBytes) FROM storage_items")
    fun getTotalSize(): Flow<Long?>

    @Query("SELECT SUM(sizeBytes) FROM storage_items WHERE category = :category")
    fun getSizeByCategory(category: String): Flow<Long?>

    @Insert
    suspend fun insertItem(item: StorageItemEntity): Long

    @Insert
    suspend fun insertItems(items: List<StorageItemEntity>)

    @Delete
    suspend fun deleteItem(item: StorageItemEntity)

    @Query("DELETE FROM storage_items WHERE id = :id")
    suspend fun deleteItemById(id: Long)

    @Query("DELETE FROM storage_items WHERE category = :category")
    suspend fun deleteByCategory(category: String)

    @Query("DELETE FROM storage_items")
    suspend fun deleteAll()
}

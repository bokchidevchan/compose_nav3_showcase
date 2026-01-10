package io.github.bokchidevchan.compose_nav3_showcase.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "storage_items")
data class StorageItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String,
    val sizeBytes: Long,
    val category: String,
    val createdAt: Long = System.currentTimeMillis()
)

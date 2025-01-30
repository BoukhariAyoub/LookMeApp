package com.lookmyup.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lookmyup.app.data.local.dao.PostDao
import com.lookmyup.app.data.local.entities.FashionItemEntity
import com.lookmyup.app.data.local.entities.PostEntity

@Database(
    entities = [PostEntity::class, FashionItemEntity::class],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}
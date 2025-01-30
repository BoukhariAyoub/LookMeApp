package com.lookmyup.app.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "fashion_items",
    indices = [Index(value = ["postId", "name"], unique = true)],
    foreignKeys = [ForeignKey(
        entity = PostEntity::class,
        parentColumns = ["id"],
        childColumns = ["postId"],
        onDelete = CASCADE
    )]
)
data class FashionItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val postId: String,
    val name: String,
    val imageUrl: String,
    val shoppingUrl: String,
    val tag: String?
)
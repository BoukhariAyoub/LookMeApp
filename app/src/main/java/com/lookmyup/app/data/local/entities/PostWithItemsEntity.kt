package com.lookmyup.app.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class PostWithItemsEntity(
    @Embedded val post: PostEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "postId"
    )
    val items: List<FashionItemEntity>
)
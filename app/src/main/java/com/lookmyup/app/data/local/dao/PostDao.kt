package com.lookmyup.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.lookmyup.app.data.local.entities.FashionItemEntity
import com.lookmyup.app.data.local.entities.PostEntity
import com.lookmyup.app.data.local.entities.PostWithItemsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(posts: List<PostEntity>)

    @Query("SELECT * FROM posts")
    fun observePosts(): Flow<List<PostEntity>>

    @Query("UPDATE posts SET likeCount = :newLikeCount, isLiked = :isLiked WHERE id = :postId")
    suspend fun updateLike(postId: String, newLikeCount: Int, isLiked: Boolean)

    @Query("UPDATE posts SET isSaved = :isSaved WHERE id = :postId")
    suspend fun updateSave(postId: String, isSaved: Boolean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<FashionItemEntity>)

    @Transaction
    @Query("SELECT * FROM posts")
    fun observePostsWithItems(): Flow<List<PostWithItemsEntity>>

    @Query("DELETE FROM fashion_items WHERE postId = :postId")
    suspend fun deleteItemsForPost(postId: String)
}
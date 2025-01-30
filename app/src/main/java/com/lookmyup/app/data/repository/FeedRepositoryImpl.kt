package com.lookmyup.app.data.repository

import com.lookmyup.app.data.local.dao.PostDao
import com.lookmyup.app.data.local.entities.FashionItemEntity
import com.lookmyup.app.data.local.entities.PostEntity
import com.lookmyup.app.data.mapper.toDomain
import com.lookmyup.app.data.mapper.toEntity
import com.lookmyup.app.data.remote.api.FeedApi
import com.lookmyup.app.domain.entities.Post
import com.lookmyup.app.domain.repository.FeedRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.util.concurrent.ConcurrentLinkedQueue

class FeedRepositoryImpl(
    private val feedApi: FeedApi,
    private val postDao: PostDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : FeedRepository {

    private val likeQueue = ConcurrentLinkedQueue<Pair<String, Boolean>>()

    override fun observePostsWithItems(): Flow<List<Post>> {
        return postDao.observePostsWithItems()
            .map { posts -> posts.map { it.toDomain() } }
    }

    override suspend fun fetchFeed() = withContext(dispatcher) {
        val remotePosts = feedApi.getFeed()

        val (postEntities, itemEntities) = remotePosts.fold(
            mutableListOf<PostEntity>() to mutableListOf<FashionItemEntity>()
        ) { (posts, items), post ->
            posts.add(post.toEntity())
            items.addAll(post.items.map { it.toEntity(post.id) })
            posts to items
        }

        postDao.insertPosts(postEntities)
        postDao.insertItems(itemEntities)
    }

    override suspend fun toggleLike(post: Post) = withContext(dispatcher) {
        val newLikeState = !post.isLiked
        val newLikeCount = if (newLikeState) post.likeCount + 1 else post.likeCount - 1

        postDao.updateLike(post.id, newLikeCount, newLikeState)

        likeQueue.add(post.id to newLikeState)
        try {
            processLikeQueue()
        } catch (exception: Throwable) {
            // reverse the local update if the request fails
            postDao.updateLike(post.id, post.likeCount, post.isLiked)
            throw exception
        }
    }

    override suspend fun toggleSave(post: Post) = withContext(dispatcher) {
        val newSaveState = !post.isSaved
        postDao.updateSave(post.id, newSaveState)
    }


    // this is only for the sake of the example,
    // in a real app we would use a more robust solution
    private suspend fun processLikeQueue() = withContext(dispatcher) {
        while (likeQueue.isNotEmpty()) {
            val (postId, isLiked) = likeQueue.poll() ?: return@withContext
            feedApi.sendLike(postId, isLiked)
        }
    }
}
package com.lookmyup.app.data.remote.api

import android.accounts.NetworkErrorException
import com.lookmyup.app.data.remote.entities.FashionItemResponse
import com.lookmyup.app.data.remote.entities.PostResponse
import kotlinx.coroutines.delay
import kotlin.random.Random

class MockFeedApi : FeedApi {

    private val sampleFashionItems = listOf(
        FashionItemResponse(
            name = "Classic Black Dress",
            imageUrl = "https://images.unsplash.com/photo-1618932260643-eee4a2f652a6?q=80&w=3024&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            shoppingUrl = "https://www.zara.com/fr/fr/sac-shopper-p16071410.html?v1=422746401",
            tag = "Elegant"
        ),
        FashionItemResponse(
            name = "Denim Jacket",
            imageUrl = "https://images.unsplash.com/photo-1543076447-215ad9ba6923?q=80&w=3264&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            shoppingUrl = "https://www.zara.com/fr/fr/sac-shopper-p16071410.html?v1=422746401",
            tag = "Casual"
        ),
        FashionItemResponse(
            name = "White Sneakers",
            imageUrl = "https://images.unsplash.com/photo-1597350584914-55bb62285896?q=80&w=2817&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            shoppingUrl = "https://www.zara.com/fr/fr/sac-shopper-p16071410.html?v1=422746401",
            tag = "Streetwear"
        ),
        FashionItemResponse(
            name = "Leather Handbag",
            imageUrl = "https://images.unsplash.com/photo-1584917865442-de89df76afd3?q=80&w=3024&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            shoppingUrl = "https://www.zara.com/fr/fr/sac-shopper-p16071410.html?v1=422746401",
            tag = "Luxury"
        ),
    )

    private val basePostList = listOf(
        PostResponse(
            id = "1",
            mediaUrl = "https://images.unsplash.com/photo-1525507119028-ed4c629a60a3?q=80&w=1944&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            mediaType = "photo",
            likeCount = 120,
            isLiked = false,
            isSaved = false,
            commentCount = 10,
            items = sampleFashionItems.take(4).distinct()
        ),
        PostResponse(
            id = "2",
            mediaUrl = "https://images.unsplash.com/photo-1517445312882-bc9910d016b7?q=80&w=3473&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            mediaType = "photo",
            likeCount = 200,
            isLiked = false,
            isSaved = false,
            commentCount = 25,
            items = sampleFashionItems.takeLast(2).distinct()
        ),
        PostResponse(
            id = "3",
            mediaUrl = "https://videos.pexels.com/video-files/30357479/13012320_1080_1920_60fps.mp4",
            mediaType = "video",
            likeCount = 200,
            isLiked = false,
            isSaved = false,
            commentCount = 25,
            items = sampleFashionItems.take(2).distinct()
        )
    )

    private val postList = (1..200).map { index ->
        val basePost = basePostList[index % basePostList.size]
        basePost.copy(id = index.toString())
    }.toMutableList()


    override suspend fun getFeed(): List<PostResponse> {
        // delay to simulate network request
        delay(2000)
        return postList
    }

    override suspend fun sendLike(postId: String, isLiked: Boolean) {
        delay(1000)
        failRandomly()
        val postIndex = postList.indexOfFirst { it.id == postId }
        val post = postList[postIndex]
        val newLikeCount = if (isLiked) post.likeCount + 1 else post.likeCount - 1
        val newPost = post.copy(likeCount = newLikeCount, isLiked = isLiked)
        postList[postIndex] = newPost
    }

    // simulate random network failures
    private fun failRandomly() {
        //random boolean
        val shouldFail = Random.nextBoolean()
        if (shouldFail) {
            throw NetworkErrorException("Random failure")
        }
    }
}
package com.lookmyup.app.data.repository

import com.lookmyup.app.data.local.dao.PostDao
import com.lookmyup.app.data.local.entities.PostEntity
import com.lookmyup.app.data.local.entities.PostWithItemsEntity
import com.lookmyup.app.data.mapper.toEntity
import com.lookmyup.app.data.remote.api.FeedApi
import com.lookmyup.app.data.remote.entities.PostResponse
import com.lookmyup.app.domain.entities.MediaType
import com.lookmyup.app.domain.entities.Post
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class FeedRepositoryImplTest {

    private lateinit var repository: FeedRepositoryImpl
    private lateinit var feedApi: FeedApi
    private lateinit var postDao: PostDao
    private val testScheduler = TestCoroutineScheduler()
    private val testDispatcher = StandardTestDispatcher(testScheduler)

    // Mocked test data
    private val remotePosts = listOf(
        PostResponse(
            id = "1", isLiked = false, isSaved = false, likeCount = 10,
            commentCount = 10, mediaType = "photo", mediaUrl = "https://example",
            items = emptyList()
        ),
        PostResponse(
            id = "2", isLiked = true, isSaved = true, likeCount = 5,
            commentCount = 10, mediaType = "video", mediaUrl = "https://example",
            items = emptyList()
        )
    )

    private val postEntities = remotePosts.map { it.toEntity() }
    private val itemEntities =
        remotePosts.flatMap { post -> post.items.map { it.toEntity(post.id) } }

    private val mockPost = Post(
        id = "1", isLiked = false, isSaved = false, likeCount = 10,
        commentCount = 10, mediaType = MediaType.PHOTO, mediaUrl = "https://example",
        items = emptyList()
    )

    private val mockPostEntity = PostEntity(
        id = "1", isLiked = false, isSaved = false, likeCount = 10,
        commentCount = 10, mediaType = "photo", mediaUrl = "https://example"
    )

    private val postWithItemsEntity = PostWithItemsEntity(
        post = mockPostEntity, items = emptyList()
    )

    @Before
    fun setUp() {
        feedApi = mockk()
        postDao = mockk(relaxed = true)
        repository = FeedRepositoryImpl(feedApi, postDao, testDispatcher)
    }

    @Test
    fun `fetchFeed should fetch and store posts`() = runTest(testScheduler) {
        // Given
        coEvery { feedApi.getFeed() } returns remotePosts

        // When
        repository.fetchFeed()

        // Then
        coVerify { postDao.insertPosts(postEntities) }
        coVerify { postDao.insertItems(itemEntities) }
    }

    @Test
    fun `toggleLike should update like state and process queue`() = runTest(testScheduler) {
        // Given
        coEvery { postDao.updateLike(mockPost.id, any(), any()) } just Runs
        coEvery { feedApi.sendLike(mockPost.id, any()) } just Runs

        // When
        repository.toggleLike(mockPost)

        // Then
        coVerify { postDao.updateLike(mockPost.id, 11, true) }
        coVerify { feedApi.sendLike(mockPost.id, true) }
    }

    @Test
    fun `toggleLike should revert changes if API fails`() = runTest(testScheduler) {
        // Given
        coEvery { postDao.updateLike(any(), any(), any()) } just Runs
        coEvery { feedApi.sendLike(any(), any()) } throws RuntimeException("Network Error")

        // When & Then
        assertThrows(
            "Network Error",
            RuntimeException::class.java,
        ) { runBlocking { repository.toggleLike(mockPost) } }

        // Verify rollback
        coVerify { postDao.updateLike(mockPost.id, mockPost.likeCount, mockPost.isLiked) }
    }

    @Test
    fun `toggleSave should update saved state in DAO`() = runTest(testScheduler) {
        // Given
        coEvery { postDao.updateSave(mockPost.id, true) } just Runs

        // When
        repository.toggleSave(mockPost)

        // Then
        coVerify { postDao.updateSave(mockPost.id, true) }
    }

    @Test
    fun `observePostsWithItems should return mapped post list`() = runTest(testScheduler) {
        // Given
        every { postDao.observePostsWithItems() } returns flowOf(listOf(postWithItemsEntity))

        // When
        val result = repository.observePostsWithItems()

        // Then
        result.collect { posts ->
            assertEquals(1, posts.size)
            assertEquals("1", posts[0].id)
        }
    }
}
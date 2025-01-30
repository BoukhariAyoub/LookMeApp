package com.lookmyup.app.di

import android.content.Context
import androidx.room.Room
import com.lookmyup.app.data.local.AppDatabase
import com.lookmyup.app.data.local.dao.PostDao
import com.lookmyup.app.data.remote.api.FeedApi
import com.lookmyup.app.data.remote.api.MockFeedApi
import com.lookmyup.app.data.repository.FeedRepositoryImpl
import com.lookmyup.app.domain.repository.FeedRepository
import com.lookmyup.app.domain.usecase.GetFeedUseCase
import com.lookmyup.app.domain.usecase.RefreshFeedUseCase
import com.lookmyup.app.domain.usecase.ToggleLikeUseCase
import com.lookmyup.app.domain.usecase.ToggleSaveUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "fashion_app_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun providePostDao(database: AppDatabase): PostDao = database.postDao()

    @Provides
    @Singleton
    fun provideFeedApi(): FeedApi = MockFeedApi()

    @Provides
    @Singleton
    fun provideFeedRepository(api: FeedApi, dao: PostDao): FeedRepository {
        return FeedRepositoryImpl(api, dao)
    }

    @Provides
    fun provideGetFeedUseCase(repository: FeedRepository): GetFeedUseCase {
        return GetFeedUseCase(repository)
    }

    @Provides
    fun provideRefreshFeedUseCase(repository: FeedRepository): RefreshFeedUseCase {
        return RefreshFeedUseCase(repository)
    }

    @Provides
    fun provideToggleLikeUseCase(repository: FeedRepository): ToggleLikeUseCase {
        return ToggleLikeUseCase(repository)
    }

    @Provides
    fun provideToggleSaveUseCase(repository: FeedRepository): ToggleSaveUseCase {
        return ToggleSaveUseCase(repository)
    }
}
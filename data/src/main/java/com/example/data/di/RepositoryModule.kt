package com.example.data.di

import com.example.data.api.BookApiService
import com.example.data.repo.KakaoBookmarkRepositoryImpl
import com.example.data.repo.KakaoSearchRepositoryImpl
import com.example.data.room.BookSearchDao
import com.example.domain.repo.KakaoBookmarkRepository
import com.example.domain.repo.KakaoSearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object RepositoryModule {

    @Provides
    @Singleton
    fun provideBookRepository(bookApiService: BookApiService): KakaoSearchRepository =
        KakaoSearchRepositoryImpl(bookApiService)


    @Provides
    @Singleton
    fun provideBookMarkRepository(bookSearchDao: BookSearchDao): KakaoBookmarkRepository =
        KakaoBookmarkRepositoryImpl(bookSearchDao)

}
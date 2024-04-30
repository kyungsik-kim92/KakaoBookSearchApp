package com.example.kakaobooksearchapp.di

import com.example.data.api.KakaoApiService
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
    fun provideBookRepository(kakaoApiService: KakaoApiService): KakaoSearchRepository =
        KakaoSearchRepositoryImpl(kakaoApiService)


    @Provides
    @Singleton
    fun provideBookMarkRepository(bookSearchDao: BookSearchDao): KakaoBookmarkRepository =
        KakaoBookmarkRepositoryImpl(bookSearchDao)

}
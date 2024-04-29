package com.example.kakaobooksearchapp.di

import com.example.kakaobooksearchapp.network.BookApiService
import com.example.kakaobooksearchapp.room.BookSearchDao
import com.example.kakaobooksearchapp.data.repo.BookmarkRepository
import com.example.kakaobooksearchapp.data.repo.BookmarkRepositoryImpl
import com.example.kakaobooksearchapp.data.repo.SearchRepository
import com.example.kakaobooksearchapp.data.repo.SearchRepositoryImpl
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
    fun provideBookRepository(bookApiService: BookApiService): SearchRepository =
        SearchRepositoryImpl(bookApiService)


    @Provides
    @Singleton
    fun provideBookMarkRepository(bookSearchDao: BookSearchDao): BookmarkRepository =
        BookmarkRepositoryImpl(bookSearchDao)

}
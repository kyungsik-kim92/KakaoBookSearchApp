package com.example.domain.repo

import com.example.domain.model.KakaoBookmark
import kotlinx.coroutines.flow.Flow

interface KakaoBookmarkRepository {
    suspend fun insertBook(item: KakaoBookmark): Long
    suspend fun deleteBook(item: KakaoBookmark): Int
    val favoriteBooks: Flow<List<KakaoBookmark>>

}
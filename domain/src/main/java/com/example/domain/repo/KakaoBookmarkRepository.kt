package com.example.domain.repo

import com.example.domain.model.KakaoBook
import com.example.domain.model.KakaoBookmark
import kotlinx.coroutines.flow.Flow

interface KakaoBookmarkRepository {
    suspend fun insertBook(item:KakaoBookmark): Long
    suspend fun deleteBook(item: KakaoBookmark): Int
    fun getFavoriteBooks(): Flow<List<KakaoBookmark>>

}
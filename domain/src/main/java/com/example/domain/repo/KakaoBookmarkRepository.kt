package com.example.domain.repo

import com.example.domain.model.KakaoBook
import com.example.domain.model.KakaoBookmark

interface KakaoBookmarkRepository {
    suspend fun insertBook(item:KakaoBookmark): Long
    suspend fun deleteBook(item: KakaoBookmark): Int
    fun getFavoriteBooks(): List<KakaoBookmark>

}
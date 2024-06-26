package com.example.domain.repo

import com.example.domain.model.KakaoSearchBook

interface KakaoSearchRepository {
    suspend fun searchBooks(
        query: String,
        sort: String,
        page: Int,
        size: Int,
    ): KakaoSearchBook

}
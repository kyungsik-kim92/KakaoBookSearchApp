package com.example.data.repo

import com.example.data.api.BookApiService
import com.example.data.mapper.toKakaoSearchBook
import com.example.domain.model.KakaoSearchBook
import com.example.domain.repo.KakaoSearchRepository
import javax.inject.Inject

class KakaoSearchRepositoryImpl @Inject constructor(private val bookApiService: BookApiService) :
    KakaoSearchRepository {
    override suspend fun searchBooks(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ): KakaoSearchBook {
        return bookApiService.searchBooks(query, sort, page, size).toKakaoSearchBook()
    }

}
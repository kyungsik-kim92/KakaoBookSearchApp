package com.example.kakaobooksearchapp.data.repo

import com.example.kakaobooksearchapp.network.BookApiService
import com.example.kakaobooksearchapp.network.response.BookSearchData
import retrofit2.Response

interface SearchRepository {
    suspend fun searchBooks(
        query: String,
        sort: String,
        page: Int,
        size: Int,
    ): Response<BookSearchData>

}
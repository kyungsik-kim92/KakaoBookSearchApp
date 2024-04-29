package com.example.kakaobooksearchapp.ui.search

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
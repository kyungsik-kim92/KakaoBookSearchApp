package com.example.kakaobooksearchapp

import com.example.kakaobooksearchapp.network.BookApiService
import com.example.kakaobooksearchapp.network.response.BookSearchData
import retrofit2.Response

class SearchRepository(private val bookApiService: BookApiService) {


    suspend fun searchBooks(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ): Response<BookSearchData> {
        return bookApiService.searchBooks(query, sort, page, size)
    }


}
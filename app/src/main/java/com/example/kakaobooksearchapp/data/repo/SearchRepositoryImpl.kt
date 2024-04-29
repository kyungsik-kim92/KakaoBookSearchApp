package com.example.kakaobooksearchapp.data.repo

import com.example.kakaobooksearchapp.network.BookApiService
import com.example.kakaobooksearchapp.network.response.BookSearchData
import retrofit2.Response
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val bookApiService: BookApiService)  :
    SearchRepository {
    override suspend fun searchBooks(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ): Response<BookSearchData> {
        return bookApiService.searchBooks(query, sort, page, size)
    }

}
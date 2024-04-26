package com.example.kakaobooksearchapp.network

import com.example.kakaobooksearchapp.constants.KakaoConstants.API_KEY
import com.example.kakaopractice.network.response.BookSearchData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface BookApiService {

    @Headers("Authorization: KakaoAK $API_KEY")
    @GET("v3/search/book")
    suspend fun searchBooks(
        @Query("query") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): Response<BookSearchData>

}
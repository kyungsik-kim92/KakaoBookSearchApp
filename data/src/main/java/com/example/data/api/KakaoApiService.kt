package com.example.data.api

import com.example.data.api.response.BookSearchData
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface KakaoApiService {
    @Headers("Authorization: KakaoAK $API_KEY")
    @GET("v3/search/book")
    suspend fun searchBooks(
        @Query("query") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): BookSearchData


    companion object {
        const val BASE_URL = "https://dapi.kakao.com/"
        const val API_KEY = "a9c9b825ab60749e299b375dc7b313e5"

    }

}
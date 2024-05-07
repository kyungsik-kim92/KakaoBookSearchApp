package com.example.data.api

import com.example.data.api.BookApiService.Companion.KakaoConstants.API_KEY
import com.example.data.api.response.BookSearchData
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    ): BookSearchData


    companion object {
        fun create(): BookApiService {
            return Retrofit.Builder()
                .baseUrl(KakaoConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BookApiService::class.java)
        }

        object KakaoConstants {
            const val BASE_URL = "https://dapi.kakao.com/"
            const val API_KEY = "a9c9b825ab60749e299b375dc7b313e5"
        }
    }


}
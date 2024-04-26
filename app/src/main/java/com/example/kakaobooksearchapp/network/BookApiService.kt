package com.example.kakaobooksearchapp.network

import com.example.kakaobooksearchapp.constants.KakaoConstants
import com.example.kakaobooksearchapp.constants.KakaoConstants.API_KEY
import com.example.kakaopractice.network.response.BookSearchData
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
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


    companion object{
        fun create() : BookApiService{
            return Retrofit.Builder()
                .baseUrl(KakaoConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BookApiService::class.java)
        }
    }



}
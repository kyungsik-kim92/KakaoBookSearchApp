package com.example.kakaobooksearchapp

import com.example.kakaobooksearchapp.network.response.KakaoBookItem



sealed class BookmarkResult{
data class AddBookmarkResult(
    val result: Boolean, val item: KakaoBookItem
) : BookmarkResult()

    data class DeleteBookmarkResult(
        val result: Boolean, val item: KakaoBookItem
    ) : BookmarkResult()

}



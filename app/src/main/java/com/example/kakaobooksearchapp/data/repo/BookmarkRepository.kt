package com.example.kakaobooksearchapp.data.repo

import com.example.kakaobooksearchapp.room.BookMarkItem
import com.example.kakaobooksearchapp.room.BookSearchDao

interface BookmarkRepository {

    suspend fun insertBook(item: BookMarkItem): Long
    suspend fun deleteBook(item: BookMarkItem): Int
    fun getFavoriteBooks(): List<BookMarkItem>

}
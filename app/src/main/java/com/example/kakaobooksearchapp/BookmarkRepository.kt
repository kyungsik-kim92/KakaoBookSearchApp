package com.example.kakaobooksearchapp

import com.example.kakaobooksearchapp.room.BookMarkItem
import com.example.kakaobooksearchapp.room.BookSearchDao

class BookmarkRepository(private var bookSearchDao: BookSearchDao) {

    suspend fun insertBook(item: BookMarkItem): Long =
        bookSearchDao.insertBook(item)

    suspend fun deleteBook(item: BookMarkItem): Int =
        bookSearchDao.deleteBook(item)

    fun getFavoriteBooks(): List<BookMarkItem> {
        return bookSearchDao.getFavoriteBooks()
    }


}
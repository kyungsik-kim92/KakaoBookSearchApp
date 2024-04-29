package com.example.kakaobooksearchapp.data.repo

import com.example.kakaobooksearchapp.room.BookMarkItem
import com.example.kakaobooksearchapp.room.BookSearchDao
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(private var bookSearchDao: BookSearchDao) :
    BookmarkRepository {
    override suspend fun insertBook(item: BookMarkItem): Long =
        bookSearchDao.insertBook(item)

    override suspend fun deleteBook(item: BookMarkItem): Int =
        bookSearchDao.deleteBook(item)

    override fun getFavoriteBooks(): List<BookMarkItem> {
        return bookSearchDao.getFavoriteBooks()
    }


}
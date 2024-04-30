package com.example.data.repo

import com.example.data.api.response.BookMarkItem
import com.example.data.mapper.toBookmarkItem
import com.example.data.mapper.toKakaoBookmark
import com.example.data.room.BookSearchDao
import com.example.domain.model.KakaoBookmark
import com.example.domain.repo.KakaoBookmarkRepository
import javax.inject.Inject

class KakaoBookmarkRepositoryImpl @Inject constructor(private var bookSearchDao: BookSearchDao) :
    KakaoBookmarkRepository {
    override suspend fun insertBook(item: KakaoBookmark): Long {
        return bookSearchDao.insertBook(item.toBookmarkItem())
    }

    override suspend fun deleteBook(item: KakaoBookmark): Int {
        return bookSearchDao.deleteBook(item.toBookmarkItem())
    }

    override fun getFavoriteBooks(): List<KakaoBookmark> {
        return bookSearchDao.getFavoriteBooks().map { it.toKakaoBookmark() }
    }


}
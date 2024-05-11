package com.example.data.repo

import com.example.data.mapper.toBookmarkItem
import com.example.data.mapper.toKakaoBookmark
import com.example.data.room.BookSearchDao
import com.example.domain.model.KakaoBookmark
import com.example.domain.repo.KakaoBookmarkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class KakaoBookmarkRepositoryImpl @Inject constructor(private var bookSearchDao: BookSearchDao) :
    KakaoBookmarkRepository {
    override suspend fun insertBook(item: KakaoBookmark): Long {
        return bookSearchDao.insertBook(item.toBookmarkItem())
    }

    override suspend fun deleteBook(item: KakaoBookmark): Int {
        return bookSearchDao.deleteBook(item.toBookmarkItem())
    }

    override val favoriteBooks: Flow<List<KakaoBookmark>>
        get() = bookSearchDao.getFavoriteBooks().map { it.map { it.toKakaoBookmark() } }


}
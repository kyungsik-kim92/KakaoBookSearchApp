package com.example.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.api.response.BookmarkItem
import kotlinx.coroutines.flow.Flow

@Dao
interface BookSearchDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(item: BookmarkItem) : Long


    @Delete
    suspend fun deleteBook(item: BookmarkItem) : Int


    @Query("SELECT * FROM book")
    fun getFavoriteBooks(): Flow<List<BookmarkItem>>

}
package com.example.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.api.response.BookMarkItem



@Dao
interface BookSearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(item: BookMarkItem) : Long


    @Delete
    suspend fun deleteBook(item: BookMarkItem) : Int


    @Query("SELECT * FROM book")
    fun getFavoriteBooks(): List<BookMarkItem>

}
package com.logituit.moviedbmvvm.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.logituit.moviedbmvvm.models.Result

@Dao
interface QuoteDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addResult(Result: Result)
    @Query("SELECT * FROM quote ORDER BY id ASC")
     fun getResult():LiveData<List<Result>>
    @Delete
    suspend fun delete(Result:Result)
}
package com.logituit.moviedbmvvm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.logituit.moviedbmvvm.models.Result
import dagger.Provides

@Database(entities = [Result::class], version = 1, exportSchema = false)
abstract class QuoteDataBase:RoomDatabase() {
    abstract fun QuoteDao():QuoteDAO
    companion object DatabaseBuilder{
        @Volatile
        private var INSTANCE:QuoteDataBase?=null

        fun getdatabase(context: Context):QuoteDataBase{
            if(INSTANCE!=null) {
                return INSTANCE!!
            }
             synchronized(this){
                 INSTANCE= Room.databaseBuilder(context.applicationContext,QuoteDataBase::class.java,
                     "QuoteDatabase")
                     .build()
                 return INSTANCE!!
             }
        }
    }
}
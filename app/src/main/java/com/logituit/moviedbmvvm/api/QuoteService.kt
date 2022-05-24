package com.logituit.moviedbmvvm.api

import com.logituit.mvvm.models.QuoteList
import com.logituit.mvvm.models.QuoteList1
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
const val API_KEY="689eeef66f23777a56e15ba2bdb4f9d8"
interface QuoteService {
    @GET("/3/movie/popular?api_key=$API_KEY")
    suspend fun getQuotes(@Query("language")language : String,@Query ( "page")page :Int): Response<QuoteList>

    @GET("/3/movie/upcoming?api_key=$API_KEY")
    suspend fun getQuotes1(@Query("language")language : String,@Query ( "page")page :Int): Response<QuoteList1>
}

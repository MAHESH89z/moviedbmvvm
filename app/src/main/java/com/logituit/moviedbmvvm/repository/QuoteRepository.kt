package com.logituit.moviedbmvvm.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.logituit.moviedbmvvm.api.QuoteService
import com.logituit.moviedbmvvm.paging.Pagingsource
import com.logituit.moviedbmvvm.paging.Pagingsource1
import com.logituit.mvvm.models.QuoteList
import com.logituit.mvvm.models.QuoteList1
import com.logituit.mvvm.models.Result
import com.logituit.mvvm.models.ResultX
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

const val NETWORK_PAGE_SIZE=20
class QuoteRepository  @Inject constructor(private val quoteService: QuoteService) {
//    private val quotesLiveData = MutableLiveData<QuoteList>()
//    val quotes: LiveData<QuoteList>
//        get() = quotesLiveData
//    private val quotesLiveData1 = MutableLiveData<QuoteList1>()
//    val quotes1: LiveData<QuoteList1>
//        get() = quotesLiveData1
//
//    suspend fun getQuotes(langauge:String,page:Int) {
//        val result = quoteService.getQuotes(langauge,page)
//        if (result.body() != null) {
//            quotesLiveData.postValue(result.body())
//        }
//    }
//
//    suspend fun getQuotes1(langauge:String,page:Int) {
//        val resultX = quoteService.getQuotes1(langauge,page)
//        if (resultX.body() != null) {
//            quotesLiveData1.postValue(resultX.body())
//        }
//    }

    fun getMovies(): LiveData<PagingData<Result>> {
        return Pager(
            config = PagingConfig(NETWORK_PAGE_SIZE, maxSize = 100),
            pagingSourceFactory = {Pagingsource(quoteService)}).liveData

    }
    fun getMovies1():LiveData<PagingData<ResultX>>{
        return Pager(
            config=PagingConfig(NETWORK_PAGE_SIZE, maxSize = 100),
            pagingSourceFactory = {Pagingsource1(quoteService)}).liveData
    }
}

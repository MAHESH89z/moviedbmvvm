package com.logituit.mvvm.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData

import androidx.paging.cachedIn
import com.logituit.moviedbmvvm.database.QuoteDAO
import com.logituit.moviedbmvvm.database.QuoteDataBase
import com.logituit.moviedbmvvm.models.Result

import com.logituit.moviedbmvvm.repository.QuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import javax.inject.Inject
import kotlin.coroutines.coroutineContext

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: QuoteRepository,private val quoteDataBase: QuoteDataBase) : ViewModel() {

    val list = repository.getMovies().cachedIn(viewModelScope)
    val list1=repository.getMovies1().cachedIn(viewModelScope)
//    fun addResult(Result:Result){
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.addresult(Result)
//        }
//    }

//    init {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.getQuotes("en-US",1)
//            repository.getQuotes1("en-US",1)
//        }
//    }
//
//    val quotes: LiveData<QuoteList>
//        get() = repository.quotes
//    val quotes1: LiveData<QuoteList1>
//        get() = repository.quotes1
//
//    }
}
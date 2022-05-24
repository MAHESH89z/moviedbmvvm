package com.logituit.mvvm.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map

import com.logituit.moviedbmvvm.repository.QuoteRepository
import com.logituit.mvvm.models.QuoteList
import com.logituit.mvvm.models.QuoteList1
import com.logituit.mvvm.models.Result
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: QuoteRepository) : ViewModel() {

    val list = repository.getMovies().cachedIn(viewModelScope)
    val list1=repository.getMovies1().cachedIn(viewModelScope)

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
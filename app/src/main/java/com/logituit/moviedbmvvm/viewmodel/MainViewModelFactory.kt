package com.logituit.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.logituit.moviedbmvvm.repository.QuoteRepository


class MainViewModelFactory(private val repository: QuoteRepository) :ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}
package com.logituit.mvvm.models

import com.logituit.moviedbmvvm.models.Result

data class QuoteList(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)
package com.logituit.mvvm.models

data class QuoteList(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)
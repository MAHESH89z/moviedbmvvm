package com.logituit.mvvm.models

data class QuoteList1(
    val page: Int,
    val results: List<ResultX>,
    val total_pages: Int,
    val total_results: Int
)
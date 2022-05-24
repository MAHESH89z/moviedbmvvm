package com.logituit.moviedbmvvm.paging

import com.logituit.mvvm.models.ResultX
import androidx.paging.*
import com.logituit.moviedbmvvm.api.QuoteService


class Pagingsource1(private val quoteService: QuoteService):PagingSource<Int,ResultX>() {
    override fun getRefreshKey(state: PagingState<Int, ResultX>): Int? {
        return state.anchorPosition?.let { anchorPosition->
            val anchorPage=state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1)?:anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultX> {
        return try {
            val prev=params.key?:95
            val response=quoteService.getQuotes1("en-Us",prev)
            if(response.isSuccessful){
                val body=response.body()?.results
                LoadResult.Page(
                    data = body!!,
                    prevKey = if(prev==1)null else prev-1,
                    nextKey = if(body.size<params.loadSize)null else prev+1
                )



            }else{LoadResult.Error(Exception())}

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}

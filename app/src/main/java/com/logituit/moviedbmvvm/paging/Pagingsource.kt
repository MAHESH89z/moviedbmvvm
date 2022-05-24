package com.logituit.moviedbmvvm.paging

import androidx.constraintlayout.helper.widget.Flow
import androidx.paging.*
import com.logituit.moviedbmvvm.api.QuoteService
import com.logituit.moviedbmvvm.repository.NETWORK_PAGE_SIZE
import com.logituit.mvvm.models.Result


class Pagingsource(private val quoteService: QuoteService):PagingSource<Int,Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition->
            val anchorPage=state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1)?:anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
       return try {
            val prev=params.key?:95
val response=quoteService.getQuotes("en-Us",prev)
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

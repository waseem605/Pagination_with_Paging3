package com.example.paging3.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging3.retrofit.QuoteAPI
import java.lang.Exception
import com.example.paging3.models.Result

class QuotePagingSource(private val quoteAPI: QuoteAPI) : PagingSource<Int, Result>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        Log.d("QuotePagingSource","load    ${params.key}")
        return try {
            val position = params.key ?: 1
            val response = quoteAPI.getQuotes(position)

            return LoadResult.Page(
                data = response.results,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == response.totalPages) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}
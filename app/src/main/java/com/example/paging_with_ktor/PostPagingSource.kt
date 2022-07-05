package com.example.paging_with_ktor

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging_with_ktor.model.PostResponse
import com.example.paging_with_ktor.network.PostService
import com.example.paging_with_ktor.utils.NetworkConstants
import javax.inject.Inject

class PostPagingSource @Inject constructor(
    private val postService: PostService
    ) : PagingSource<Int, PostResponse>() {
    override fun getRefreshKey(state: PagingState<Int, PostResponse>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PostResponse> {
        return try {
            val page = params.key ?: NetworkConstants.FIRST_PAGE

            LoadResult.Page(
                data = postService.getPost(page),
                prevKey = if (page == NetworkConstants.FIRST_PAGE) null else page-1,
                nextKey = if (page == NetworkConstants.LAST_PAGE) null else page+1,
            )
        }
        catch (e : Exception){
            LoadResult.Error(e)
        }
    }
}
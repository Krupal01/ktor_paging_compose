package com.example.paging_with_ktor.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.paging_with_ktor.PostPagingSource
import com.example.paging_with_ktor.model.PostResponse
import com.example.paging_with_ktor.network.PostService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val postPagingSource: PostPagingSource
) : ViewModel() {

    val postPagingFlow : Flow<PagingData<PostResponse>> = Pager(PagingConfig(
        pageSize = 10
    )){postPagingSource}.flow.cachedIn(viewModelScope)


//    private val _postList = MutableLiveData<List<PostResponse>>()
//    val postList  = _postList as LiveData<List<PostResponse>>
//
//    init {
//        getAllPost()
//    }
//
//    private fun getAllPost(){
//        viewModelScope.launch {
//            _postList.value = ArrayList(postService.getPost())
//        }
//    }
}
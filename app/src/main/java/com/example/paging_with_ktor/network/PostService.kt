package com.example.paging_with_ktor.network

import com.example.paging_with_ktor.model.PostResponse

interface PostService {
    suspend fun getPost():List<PostResponse>
}
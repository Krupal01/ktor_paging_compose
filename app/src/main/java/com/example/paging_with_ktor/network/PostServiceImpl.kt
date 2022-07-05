package com.example.paging_with_ktor.network

import android.util.Log
import com.example.paging_with_ktor.model.PostResponse
import com.example.paging_with_ktor.utils.NetworkConstants
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class PostServiceImpl(
    private val client: HttpClient
) : PostService {

    override suspend fun getPost(userId : Int): List<PostResponse> {
        return try {
            client.get {
                url(NetworkConstants.POSTS)
                parameter(NetworkConstants.USERID_KEY , userId)
            }.body()
        }catch (e : Exception){
            Log.i("Client Exception",e.toString())
            emptyList()
        }
    }
}
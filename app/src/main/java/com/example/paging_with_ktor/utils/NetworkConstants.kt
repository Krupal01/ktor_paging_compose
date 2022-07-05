package com.example.paging_with_ktor.utils

object NetworkConstants {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com"
    const val POSTS = "$BASE_URL/posts"
    const val USERID_KEY = "userId"
    const val FIRST_PAGE = 1
    const val LAST_PAGE = 10
}
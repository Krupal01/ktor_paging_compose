package com.example.paging_with_ktor.module

import com.example.paging_with_ktor.network.PostService
import com.example.paging_with_ktor.network.PostServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.ContentType.Application.Json
import io.ktor.serialization.kotlinx.*
import kotlinx.serialization.json.Json
import javax.inject.Singleton
var converter: KotlinxSerializationConverter? = null
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun getClient() = HttpClient(CIO){
        install(Logging){
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            converter = KotlinxSerializationConverter(Json {
                prettyPrint = true
                ignoreUnknownKeys = true
                explicitNulls = false
            })
            register(Json, converter!!)
        }
    }


    @Provides
    @Singleton
    fun getPostService(client: HttpClient) : PostService{
        return PostServiceImpl(client = client)
    }
}
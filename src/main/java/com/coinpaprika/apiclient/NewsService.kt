package com.coinpaprika.apiclient

import com.coinpaprika.apiclient.entity.NewsEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("news/latest/")
    suspend fun getNews(@Query("limit") limit: Int): List<NewsEntity>
}
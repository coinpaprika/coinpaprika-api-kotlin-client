package com.coinpaprika.apiclient

import com.coinpaprika.apiclient.entity.NewsEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiContract {
    @GET("news/latest/")
    fun getNews(@Query("limit") limit: Int): Single<List<NewsEntity>>
}
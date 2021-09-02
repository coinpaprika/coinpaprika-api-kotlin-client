package com.coinpaprika.apiclient.repository.news

import com.coinpaprika.apiclient.entity.NewsEntity
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiContract {
    @GET("news/latest/")
    fun getNews(@Query("limit") limit: Int): Observable<Response<List<NewsEntity>>>
}
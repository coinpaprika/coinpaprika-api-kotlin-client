package com.coinpaprika.apiclient.repository.search

import com.coinpaprika.apiclient.entity.SearchEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiContract {
    @GET("search/?c=currencies,icos,people,tags")
    suspend fun getSearches(@Query("q") query: String): SearchEntity
}